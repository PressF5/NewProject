package org.project.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {
    public static Map<String, String> convertPropertyFileToMap(String namePropertyFile) {
        Map<String, String> propertyMap = new HashMap<>();
        Properties prop = null;
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(namePropertyFile)) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            propertyMap.put((String) entry.getKey(), (String) entry.getValue());
        }
        return propertyMap;
    }
}
