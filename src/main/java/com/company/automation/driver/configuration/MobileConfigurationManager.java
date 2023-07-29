package com.company.automation.driver.configuration;

import com.company.automation.configuration.ConfigurationManager;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * MobileConfigurationManager is a Singleton class that provides mobile-specific capabilities.
 * This class helps retrieve the mobile configuration parameters defined in a properties file.
 */
public class MobileConfigurationManager {

    private static MobileConfigurationManager instance = null;

    private DesiredCapabilities mobileCapabilities;

    private MobileConfigurationManager() {
        mobileCapabilities = new DesiredCapabilities();

        // Assume you have these properties defined in your properties file
        String deviceName = ConfigurationManager.getInstance().getProperty("deviceName");
        String platformVersion = ConfigurationManager.getInstance().getProperty("platformVersion");
        String appPath = ConfigurationManager.getInstance().getProperty("appPath");

        mobileCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        mobileCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        mobileCapabilities.setCapability(MobileCapabilityType.APP, appPath);
    }

    public static MobileConfigurationManager getInstance() {
        if (instance == null) {
            instance = new MobileConfigurationManager();
        }
        return instance;
    }

    public DesiredCapabilities getMobileCapabilities() {
        return mobileCapabilities;
    }
}
