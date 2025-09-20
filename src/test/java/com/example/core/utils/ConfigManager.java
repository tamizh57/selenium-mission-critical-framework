package com.example.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final ThreadLocal<Properties> properties = ThreadLocal.withInitial(Properties::new);

    static {
        loadConfig();
    }

    private static void loadConfig() {
        Properties props = new Properties();
        try {
            // Load base properties first
            props.load(new FileInputStream("src/test/resources/config/base.properties"));

            // Determine environment from system property or default to 'dev'
            String env = System.getProperty("env", "dev").toLowerCase();

            // Load environment-specific properties, overriding base values
            props.load(new FileInputStream("src/test/resources/config/" + env + ".properties"));

            properties.set(props);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config files", e);
        }
    }

    // Retrieve a value
    public static String get(String key) {
        return properties.get().getProperty(key);
    }

    // Retrieve as int
    public static int getInt(String key) {
        return Integer.parseInt(properties.get().getProperty(key));
    }

    // Retrieve base URL
    public static String getBaseUrl() {
        return get("base.url");
    }

    // Reload config for the current thread (optional)
    public static void reload() {
        loadConfig();
    }

    // Clean up for parallel execution
    public static void remove() {
        properties.remove();
    }

}
