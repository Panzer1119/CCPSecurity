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

package de.codemakers.ccpsecurity.tiles;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;
import de.codemakers.ccpsecurity.utils.AES;
import de.codemakers.ccpsecurity.utils.ICCPSPeripheral;
import de.codemakers.ccpsecurity.utils.Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import java.util.Map;

public class TileEntityAESCryptoBlock extends TileEntity implements ICCPSPeripheral {
    
    private ITurtleAccess turtle;
    
    public TileEntityAESCryptoBlock() {
        super();
    }
    
    public TileEntityAESCryptoBlock(ITurtleAccess turtle) {
        this.turtle = turtle;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }
    
    @Nonnull
    @Override
    public String getType() {
        return "aesCryptoBlock";
    }
    
    @Nonnull
    @Override
    public String[] getMethodNames() {
        return new String[] {"generateAESKey", "randomAESKey", "encryptAESGCM", "decryptAESGCM"};
    }
    
    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computer, @Nonnull ILuaContext context, int method, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        SecretKey key = null;
        switch (method) {
            case 0:
                if (arguments.length >= 1 && arguments.length <= 2) {
                    if (!(arguments[0] instanceof String) && arguments[0] != null) {
                        throw new LuaException("'generateAESKey' takes a string as the first argument");
                    } else if (arguments.length >= 2 && !(arguments[1] instanceof Double)) {
                        throw new LuaException("'generateAESKey' takes a number as the second argument");
                    } else {
                        if (arguments[0] == null) {
                            if (arguments.length > 1) {
                                final int size = ((int) ((double) arguments[1]));
                                key = getSecretKey(size);
                            } else {
                                key = AES.generate128Key();
                            }
                        } else {
                            key = AES.generateKey("" + arguments[0], arguments.length <= 1 ? -1 : (int) ((double) arguments[1]));
                        }
                        if (key == null) {
                            return new Object[] {null};
                        }
                        return new Object[] {Utils.toByteLuaArray(key.getEncoded()), key.getAlgorithm(), key.getFormat()};
                    }
                } else if (arguments.length == 0) {
                    throw new LuaException("'generateAESKey' needs at least 1 argument");
                } else {
                    throw new LuaException("'generateAESKey' only accepts 1 or 2 arguments");
                }
            case 1:
                if (arguments.length <= 1) {
                    if (arguments.length >= 1 && !(arguments[0] instanceof Double)) {
                        throw new LuaException("'randomAESKey' takes a number as the first argument");
                    } else {
                        if (arguments.length > 0) {
                            final int size = ((int) ((double) arguments[0]));
                            key = getSecretKey(size);
                        } else {
                            key = AES.generate128Key();
                        }
                        if (key == null) {
                            return new Object[] {null};
                        }
                        return new Object[] {Utils.toByteLuaArray(key.getEncoded()), key.getAlgorithm(), key.getFormat()};
                    }
                } else {
                    throw new LuaException("'randomAESKey' only accepts up to 1 argument");
                }
            case 2:
                if (arguments.length == 2) {
                    if (!(arguments[0] instanceof Map)) {
                        throw new LuaException("'encryptAESGCM' takes a string as the first argument");
                    } else if (!(arguments[1] instanceof String)) {
                        throw new LuaException("'encryptAESGCM' takes a string as the second argument");
                    } else {
                        try {
                            final byte[] encrypted = AES.encrypt(AES.unserialiseKey((Map<Double, Object>) arguments[0]), ("" + arguments[1]).getBytes());
                            return new Object[] {Utils.toByteLuaArray(encrypted), true};
                        } catch (Exception ex) {
                            return new Object[] {null, false, ex.toString()};
                        }
                    }
                } else {
                    throw new LuaException("'encryptAESGCM' needs 2 arguments");
                }
            case 3:
                if (arguments.length == 2) {
                    if (!(arguments[0] instanceof Map)) {
                        throw new LuaException("'decryptAESGCM' takes a string as the first argument");
                    } else if (!(arguments[1] instanceof String)) {
                        throw new LuaException("'decryptAESGCM' takes a string as the second argument");
                    } else {
                        try {
                            final byte[] decrypted = AES.decrypt(AES.unserialiseKey((Map<Double, Object>) arguments[0]), ("" + arguments[1]).getBytes());
                            return new Object[] {new String(decrypted), true};
                        } catch (Exception ex) {
                            return new Object[] {null, false, ex.toString()};
                        }
                    }
                } else {
                    throw new LuaException("'decryptAESGCM' needs 2 arguments");
                }
        }
        return new Object[0];
    }
    
    private SecretKey getSecretKey(int size) {
        SecretKey key;
        if (size == 256) {
            key = AES.generate256Key();
        } else if (size == 192) {
            key = AES.generate192Key();
        } else {
            key = AES.generate128Key();
        }
        return key;
    }
    
    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return this == other;
    }
    
}
