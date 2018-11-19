package com.ava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BiMapTest {
    @Test
    public void testLogMap() {
        Map<Integer,String> logfileMap = Maps.newHashMap();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        System.out.println(logfileMap);
        Map<String,Integer> logfileReverseMap = Maps.newHashMap();
        logfileReverseMap = getReverseMap(logfileMap);
        System.out.println(logfileReverseMap);
    }

    @Test
    public void testBiMap() {
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"d.log");
        logfileMap.put(2,"e.log");
        logfileMap.put(3,"f.log");
        System.out.println(logfileMap);
        BiMap<String,Integer> logfileInverseMap = logfileMap.inverse();
        System.out.println(logfileInverseMap);
        logfileMap.put(4,"g.log");
        System.out.println(logfileMap);
        System.out.println(logfileInverseMap);
    }

    @Test
    public void testBiMapMethod() {
        BiMap<Integer,String> biMap = HashBiMap.create();
        biMap.put(1,"aaa");
        biMap.put(2,"bbb");
        biMap.put(3,"ccc");
        biMap.forcePut(4,"ccc");
        System.out.println(biMap);
    }

    /**
     * 倒置map
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map<V,K> getReverseMap(Map<K,V> map) {
        Map<V,K> reverseMap = new HashMap<>();
        for (Map.Entry<K,V> entry : map.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap;
    }
}
