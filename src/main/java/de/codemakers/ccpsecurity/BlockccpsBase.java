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

import com.austinv11.collectiveframework.minecraft.blocks.BlockBase;
import de.codemakers.ccpsecurity.blocks.Blockccps;
import de.codemakers.ccpsecurity.creativetab.CreativeTabCCPS;
import de.codemakers.ccpsecurity.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;

public class BlockccpsBase extends BlockBase implements Blockccps {
    
    @Override
    public CreativeTabs getTab() {
        return CreativeTabCCPS.CCPS_TAB;
    }
    
    @Override
    public String getModId() {
        return Reference.MOD_ID;
    }
    
}
