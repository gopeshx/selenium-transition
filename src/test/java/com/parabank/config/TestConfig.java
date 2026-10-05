package com.parabank.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
    private static final Properties PROPERTIES = loadProperties();

    private TestConfig() {
    }

    public static String get(String key) {
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream stream = TestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (stream == null) {
                throw new IllegalStateException("config.properties was not found on the test classpath");
            }
            properties.load(stream);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load test configuration", exception);
        }
    }
}