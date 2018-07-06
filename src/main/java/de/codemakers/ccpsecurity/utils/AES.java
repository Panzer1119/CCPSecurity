/*
 *    Copyright 2018 Paul Hagedorn (Panzer1119)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package de.codemakers.ccpsecurity.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;

public class AES {
    
    public static MessageDigest SHA_256;
    public static MessageDigest SHA_512;
    public static KeyGenerator keyGenerator_128;
    public static KeyGenerator keyGenerator_192;
    public static KeyGenerator keyGenerator_256;
    
    static {
        try {
            SHA_256 = MessageDigest.getInstance("SHA-256");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            SHA_512 = MessageDigest.getInstance("SHA-512");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            keyGenerator_128 = KeyGenerator.getInstance("AES");
            keyGenerator_128.init(128);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            keyGenerator_192 = KeyGenerator.getInstance("AES");
            keyGenerator_192.init(192);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            keyGenerator_256 = KeyGenerator.getInstance("AES");
            keyGenerator_256.init(256);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static SecretKeySpec generateKey(String key, int size) {
        try {
            return generateKey(key.getBytes("UTF-8"), size);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static SecretKeySpec generateKey(byte[] key, int size) {
        if (key == null || key.length == 0) {
            return null;
        }
        if (size != 128 && size != 256) {
            size = 128;
        }
        return new SecretKeySpec(Arrays.copyOf(SHA_256.digest(key), size / 8), "AES");
    }
    
    public static SecretKey generate128Key() {
        return keyGenerator_128.generateKey();
    }
    
    public static SecretKey generate192Key() {
        return keyGenerator_192.generateKey();
    }
    
    public static SecretKey generate256Key() {
        return keyGenerator_256.generateKey();
    }
    
    public static SecretKey unserialiseKey(Map<Double, Object> key) {
        return unserialiseKey(key, "AES");
    }
    
    public static SecretKey unserialiseKey(Map<Double, Object> key, String algorithm) {
        return new SecretKeySpec(Utils.fromByteLuaArray(key), algorithm);
    }
    
    public static byte[] encrypt(SecretKey key, byte[] data) throws Exception {
        final int ivSize = 12;
        final byte[] iv = new byte[ivSize];
        final SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        final GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmParameterSpec);
        final byte[] encrypted = cipher.doFinal(data);
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4 + iv.length + encrypted.length);
        byteBuffer.putInt(iv.length);
        byteBuffer.put(iv);
        byteBuffer.put(encrypted);
        final byte[] encryptedIVAndText = byteBuffer.array();
        Arrays.fill(encrypted, (byte) 0);
        byteBuffer.clear();
        return encryptedIVAndText;
    }
    
    public static byte[] decrypt(SecretKey key, byte[] encryptedIVAndText) throws Exception {
        final ByteBuffer byteBuffer = ByteBuffer.wrap(encryptedIVAndText);
        final int ivSize = byteBuffer.getInt();
        final byte[] iv = new byte[ivSize];
        byteBuffer.get(iv);
        final byte[] encrypted = new byte[byteBuffer.remaining()];
        byteBuffer.get(encrypted);
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(128, iv));
        final byte[] data = cipher.doFinal(encrypted);
        byteBuffer.clear();
        Arrays.fill(encrypted, (byte) 0);
        return data;
    }
    
    public static byte[] encryptCBC(SecretKey key, byte[] data) throws Exception {
        final int ivSize = 16;
        final byte[] iv = new byte[ivSize];
        final SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(data);
        byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
        return encryptedIVAndText;
    }
    
    public static byte[] decryptCBC(SecretKey key, byte[] encryptedIVAndText) throws Exception {
        int ivSize = 16;
        final byte[] iv = new byte[ivSize];
        System.arraycopy(encryptedIVAndText, 0, iv, 0, iv.length);
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        int encryptedSize = encryptedIVAndText.length - ivSize;
        final byte[] encrypted = new byte[encryptedSize];
        System.arraycopy(encryptedIVAndText, ivSize, encrypted, 0, encryptedSize);
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        return cipher.doFinal(encrypted);
    }
    
}
