package com.company.automation.driver.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;
import com.company.automation.configuration.ConfigurationManager;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The RemoteConfigurationManager class handles configuration for remote WebDriver instances.
 * It is responsible for setting the capabilities of the remote WebDriver and the hub URL.
 * This class is particularly useful when you want to execute your tests on remote machines
 * (for example, in a Selenium Grid or a cloud-based solution).
 */
public class RemoteConfigurationManager {

    // Default URL of the Selenium Grid Hub
    private static final String DEFAULT_HUB_URL = "http://localhost:4444/wd/hub";

    /**
     * This method is responsible for setting up the DesiredCapabilities object for the remote WebDriver.
     * The capabilities are set based on the properties defined in the configuration file.
     * If a property is not defined in the configuration file, it will not be set in the DesiredCapabilities object.
     * @return DesiredCapabilities - the capabilities for the remote WebDriver
     */
    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Fetch the browser details from the config file and set them in the capabilities
        String browserName = ConfigurationManager.getInstance().getProperty("browser.name");
        if (browserName != null) {
            capabilities.setCapability("browserName", browserName);
        }

        String browserVersion = ConfigurationManager.getInstance().getProperty("browser.version");
        if (browserVersion != null) {
            capabilities.setCapability("version", browserVersion);
        }

        String platformName = ConfigurationManager.getInstance().getProperty("platform.name");
        if (platformName != null) {
            capabilities.setCapability("platform", platformName);
        }

        // Fetch additional settings from the config file and set them in the capabilities
        String screenResolution = ConfigurationManager.getInstance().getProperty("screen.resolution");
        if (screenResolution != null) {
            capabilities.setCapability("screenResolution", screenResolution);
        }

        String networkCondition = ConfigurationManager.getInstance().getProperty("network.condition");
        if (networkCondition != null) {
            capabilities.setCapability("networkCondition", networkCondition);
        }

        // If you are using a service that requires authentication, set them here.
        String username = ConfigurationManager.getInstance().getProperty("grid.username");
        String accessKey = ConfigurationManager.getInstance().getProperty("grid.accessKey");
        if (username != null && accessKey != null) {
            capabilities.setCapability("username", username);
            capabilities.setCapability("accessKey", accessKey);
        }

        return capabilities;
    }

    /**
     * This method returns the URL of the Selenium Grid Hub.
     * If the URL is not defined in the configuration file, a default URL is used.
     * @return URL - the URL of the Selenium Grid Hub
     */
    public URL getHubURL() {
        String hubURL = ConfigurationManager.getInstance().getProperty("hub.url");
        try {
            return new URL(hubURL != null ? hubURL : DEFAULT_HUB_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid HUB URL: " + hubURL, e);
        }
    }
}
