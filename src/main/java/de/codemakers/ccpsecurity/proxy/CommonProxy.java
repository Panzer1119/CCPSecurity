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

package de.codemakers.ccpsecurity.proxy;

import com.austinv11.collectiveframework.minecraft.client.gui.GuiHandler;
import de.codemakers.ccpsecurity.CCPSecurity;
import de.codemakers.ccpsecurity.reference.Reference;
import de.codemakers.ccpsecurity.tiles.TileEntityAESCryptoBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    
    public void setupVillagers() {
        /*
        VillagerRegistry.VillagerProfession profession = new VillagerProfessionPPP(
                Reference.MOD_ID + ":ccvillager",
                Reference.MOD_ID + ":textures/models/ccvillager.png",
                "minecraft:textures/entity/zombie_villager/zombie_villager.png");
        ForgeRegistries.VILLAGER_PROFESSIONS.register(profession);
        */
    }
    
    public void registerTileEntities() {
        registerTileEntity(TileEntityAESCryptoBlock.class);
    }
    
    private void registerTileEntity(Class<? extends TileEntity> tileEntity) {
        GameRegistry.registerTileEntity(tileEntity, String.format("%s:%s", Reference.MOD_ID, tileEntity.getSimpleName()));
    }
    
    public void textureAndModelInit() {
    }
    
    public void registerRenderers() {
    }
    
    public void prepareGuis() {
        NetworkRegistry.INSTANCE.registerGuiHandler(CCPSecurity.instance, new GuiHandler());
    }
    
    public void registerEvents() {
    }
    
    public void registerEntities() {
    }
    
    public void registerNetwork() {
    }
    
    public void registerCapabilities() {
    }
    
}
