package com.example.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final ThreadLocal<Properties> properties = ThreadLocal.withInitial(Properties::new);

    static {
        loadConfig();
    }

    /**
     * Load base + env config, merge, and apply system property overrides
     */
    private static void loadConfig() {
        Properties props = new Properties();
        String activeEnv = "dev"; // default

        try {
            // Load base config
            try (FileInputStream baseFis = new FileInputStream("src/test/resources/config/base.properties")) {
                props.load(baseFis);
            }

            //  Determine environment: system property overrides base
            String envFromSystem = System.getProperty("env");
            String envFromBase = props.getProperty("env", "dev");
            activeEnv = (envFromSystem != null) ? envFromSystem.toLowerCase() : envFromBase.toLowerCase();
            System.out.println("Active environment: " + activeEnv);

            // Load env-specific properties (overrides base)
            try (FileInputStream envFis = new FileInputStream(
                    "src/test/resources/config/" + activeEnv + ".properties")) {
                props.load(envFis);
            }

            // Apply system property overrides (optional)
            System.getProperties().forEach((k, v) -> props.setProperty((String) k, (String) v));

            //  Set in ThreadLocal for parallel safety
            properties.set(props);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration files", e);
        }
    }

    // ---------- GETTERS ----------

    /** Get property as String; can provide default */
    public static String get(String key) {
        return properties.get().getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return properties.get().getProperty(key, defaultValue);
    }

    /** Get property as int; can provide default */
    public static int getInt(String key) {
        return Integer.parseInt(properties.get().getProperty(key));
    }

    public static int getInt(String key, int defaultValue) {
        String val = properties.get().getProperty(key);
        return (val != null) ? Integer.parseInt(val) : defaultValue;
    }

    /** Convenience method to get base URL */
    public static String getBaseUrl() {
        return get("base.url");
    }

    // ---------- THREAD-LIFECYCLE METHODS ----------

    /** Reload config for current thread (optional) */
    public static void reload() {
        loadConfig();
    }

    /** Remove ThreadLocal config (important for parallel execution) */
    public static void remove() {
        properties.remove();
    }
}
