package com.opencampus.automation.core;

import com.opencampus.automation.enums.ENV;
import com.opencampus.automation.enums.ENV_KEY;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProperties {
    private static final ENV envName;
    private static final Properties envProperties;

    static {
        envName = ENV.PROD;
        envProperties = getEnvProperties();
    }

    public static String getEnvProp(ENV_KEY key) {
        String value = envProperties.getProperty(key.name());
        if (value == null)
            throw new RuntimeException("Environment key " + key.name() + " is not existed");
        return value;
    }

    private static Properties getEnvProperties() {
        try {
            Properties envProperties = new Properties();
            String propertiesPath = envName.getName() + ".properties";
            InputStream inputStream = ConfigurationProperties.class.getClassLoader().getResourceAsStream(propertiesPath);
            envProperties.load(inputStream);
            return envProperties;
        } catch (Exception e) {
            throw new RuntimeException("Unable to load env properties. " + e);
        }
    }
}
