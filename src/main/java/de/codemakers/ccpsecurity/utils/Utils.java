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

import java.util.HashMap;
import java.util.Map;

public class Utils {
    
    public static Map<String, Object> emptyLuaTable() {
        return new HashMap<>();
    }
    
    public static Map<Integer, Object> luaArray(Object[] array) {
        final Map<Integer, Object> map = new HashMap<>();
        int i = 1;
        for (Object object : array) {
            map.put(i, object);
            i++;
        }
        return map;
    }
    
    public static Map<Integer, Object> toByteLuaArray(byte[] array) {
        final Map<Integer, Object> map = new HashMap<>();
        int i = 1;
        for (Object object : array) {
            map.put(i, object);
            i++;
        }
        return map;
    }
    
    public static Object[] fromLuaArray(Map<Double, Object> luaArray) {
        int highest = -1;
        for (double i : luaArray.keySet()) {
            highest = Math.max(highest, (int) i);
        }
        final Object[] array = new Object[highest];
        for (Map.Entry<Double, Object> entry : luaArray.entrySet()) {
            array[(int) Math.floor(entry.getKey()) - 1] = entry.getValue();
        }
        return array;
    }
    
    public static byte[] fromByteLuaArray(Map<Double, Object> luaArray) {
        int highest = -1;
        for (double i : luaArray.keySet()) {
            highest = Math.max(highest, (int) i);
        }
        final byte[] array = new byte[highest];
        for (Map.Entry<Double, Object> entry : luaArray.entrySet()) {
            array[(int) Math.floor(entry.getKey()) - 1] = (byte) ((double) entry.getValue());
        }
        return array;
    }
    
}
