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
import de.codemakers.ccpsecurity.utils.ICCPSPeripheral;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
        return new String[] {"generateKey", "test"};
    }
    
    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computer, @Nonnull ILuaContext context, int method, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        switch (method) {
            case 0:
                return new Object[0];
            case 1:
                return new Object[] {"Test: " + Math.random()};
        }
        return new Object[0];
    }
    
    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return this == other;
    }
    
}
