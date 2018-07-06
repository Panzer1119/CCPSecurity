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

package de.codemakers.ccpsecurity.init;

import de.codemakers.ccpsecurity.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.minecraftforge.fml.common.registry.ForgeRegistries.ITEMS;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    
    public static final Item AES_CRYPTO_BLOCK = getItemBlock(ModBlocks.AES_CRYPTO_BLOCK);
    
    public static void register() {
        ITEMS.register(AES_CRYPTO_BLOCK);
    }
    
    public static void registerModels() {
        registerModel(AES_CRYPTO_BLOCK);
    }
    
    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
    
    private static Item getItemBlock(Block block) {
        return new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName());
    }
    
}
