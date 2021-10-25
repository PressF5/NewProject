package org.project.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReadWriteProperty {
    private static Map<String, String> map = new ConcurrentHashMap<>();

    public static void write(String key, String value){
        map.put(key, value);
    }

    public static String read(String key){
        return map.get(key);
    }
}
