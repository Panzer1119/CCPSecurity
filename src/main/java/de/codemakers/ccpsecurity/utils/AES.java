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

import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;

public class AES {
    
    public static MessageDigest SHA_256;
    public static MessageDigest SHA_512;
    
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
    
}
