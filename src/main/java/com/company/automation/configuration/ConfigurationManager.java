package com.company.automation.configuration;// Import necessary classes
import com.company.automation.common.LoggerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigurationManager is a Singleton class that provides application configuration parameters.
 * This class helps retrieve the configuration parameters defined either in a properties file,
 * System properties or environment variables.

 * The configuration parameters can be used for various settings like:
 * - database connection details
 * - setting timeout values
 * - setting file paths
 * etc.
 */
public class ConfigurationManager {

    // This is the Singleton instance.
    private static ConfigurationManager instance = null;

    // Hold the properties read from the config file
    private Properties properties;

    // The LOCK object for synchronization
    private static final Object LOCK = new Object();

    private ConfigurationManager() {
        // Private constructor to restrict instantiation
        synchronized (LOCK) {
            // Instantiate the properties object
            properties = new Properties();

            try {
                // Use try-with-resources block for automatic resource management (ARM)
                // Open a FileInputStream to read the config file
                try (FileInputStream in = new FileInputStream("./src/main/resources/config.properties")) {
                    // Load the properties from the config file into the Properties object
                    properties.load(in);
                }
            } catch (IOException e) {
                // Log the exception using a Logger utility class
                LoggerUtil.logError("Failed to load properties file", e);
            }
        }
    }

    // The static method to return the Singleton instance
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public String getDownloadDir() {
        String downloadDir = properties.getProperty("download_dir");
        if (downloadDir != null) {
            return downloadDir;
        } else {
            // Default to a directory if no download_dir specified in the config
            return System.getProperty("user.dir") + "/downloads";
        }
    }

    public String getProperty(String key) {
        String systemPropertyValue = System.getProperty(key);
        if (systemPropertyValue != null) {
            return systemPropertyValue;
        }

        String envPropertyValue = System.getenv(key);
        if (envPropertyValue != null) {
            return envPropertyValue;
        }

        String propertyValue = properties.getProperty(key);
        if (propertyValue == null) {
            LoggerUtil.logError("Property not found: " + key);
            throw new RuntimeException("Property not found: " + key);
        }

        return propertyValue;
    }
}

