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

package de.codemakers.ccpsecurity;

import de.codemakers.ccpsecurity.init.ModBlocks;
import de.codemakers.ccpsecurity.init.ModItems;
import de.codemakers.ccpsecurity.init.ModPeripherals;
import de.codemakers.ccpsecurity.init.Recipes;
import de.codemakers.ccpsecurity.reference.Reference;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = CCPSecurity.MOD_ID, name = CCPSecurity.NAME, version = CCPSecurity.VERSION, dependencies = "required-after:theframework;required-after:computercraft")
public class CCPSecurity {
    
    public static final String MOD_ID = "ccpsecurity";
    public static final String NAME = "ComputerCraft Plus Security";
    public static final String VERSION = "0.1";
    
    public static Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);
    
    @Mod.Instance(Reference.MOD_ID)
    public static CCPSecurity instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        Reference.LOGGER = LOGGER;
        LOGGER.info("Hey i'm " + NAME);
        ModBlocks.register();
        ModItems.register();
        ModPeripherals.registerInternally();
    }
    
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        ModPeripherals.registerWithComputerCraft();
        LOGGER.info("All peripherals and TURTLE upgrades registered!");
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("See you later " + NAME);
        Recipes.init();
    }
    
    
}
