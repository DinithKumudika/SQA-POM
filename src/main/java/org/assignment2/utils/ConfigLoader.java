package org.assignment2.utils;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader(System.getProperty("user.dir") + "\\src\\main\\java\\org\\assignment2\\config\\config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public Properties getProperties(){
        return  properties;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("url");
        if(prop != null) return prop;
        else throw new RuntimeException("baseUrl is not specified in the config.properties");
    }
}
