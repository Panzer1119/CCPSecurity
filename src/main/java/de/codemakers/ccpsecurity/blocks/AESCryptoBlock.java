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

package de.codemakers.ccpsecurity.blocks;

import de.codemakers.ccpsecurity.BlockccpsBase;
import de.codemakers.ccpsecurity.CCPSecurity;
import de.codemakers.ccpsecurity.tiles.TileEntityAESCryptoBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AESCryptoBlock extends BlockccpsBase implements ITileEntityProvider {
    
    public AESCryptoBlock() {
        this.setRegistryName(CCPSecurity.MOD_ID, "aes_crypto_block");
        this.setUnlocalizedName("aes_crypto_block");
    }
    
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityAESCryptoBlock();
    }
    
}
