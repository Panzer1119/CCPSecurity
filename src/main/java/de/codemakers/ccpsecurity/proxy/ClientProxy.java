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

import com.austinv11.collectiveframework.minecraft.utils.ModelManager;
import com.austinv11.collectiveframework.minecraft.utils.TextureManager;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import de.codemakers.ccpsecurity.init.ModItems;
import de.codemakers.ccpsecurity.init.ModPeripherals;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
    
    
    @SideOnly(Side.CLIENT)
    @Override
    public void setupVillagers() {
        super.setupVillagers();
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void textureAndModelInit() {
        for (ITurtleUpgrade upgrade : ModPeripherals.TURTLE_UPGRADES) {
            if (upgrade instanceof TextureManager.TextureRegistrar) {
                TextureManager.register((TextureManager.TextureRegistrar) upgrade);
            }
            if (upgrade instanceof ModelManager.ModelRegistrar) {
                ModelManager.register((ModelManager.ModelRegistrar) upgrade);
            }
        }
        ModItems.registerModels();
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerRenderers() {
        //		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket());
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void prepareGuis() {
        super.prepareGuis();
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerEvents() {
        super.registerEvents();
    }
    
}
