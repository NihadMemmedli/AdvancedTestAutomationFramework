package com.company.automation.driver.configuration;

import com.company.automation.configuration.ConfigurationManager;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * CloudConfigurationManager is a configuration class responsible for managing the settings
 * and capabilities for browsers when running tests on cloud-based solutions like BrowserStack,
 * Sauce Labs, etc.
 *
 * This includes the management of platform details, version, browser details,
 * and any additional capabilities or settings that the specific cloud service might require.
 */
public class CloudConfigurationManager {

    /**
     * Sets up and returns the capabilities for cloud-based test execution.
     *
     * @return DesiredCapabilities for cloud-based execution
     */
    public DesiredCapabilities getCloudCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String platform = ConfigurationManager.getInstance().getProperty("cloud.platform");
        if (platform != null) {
            capabilities.setCapability("platform", platform);
        }

        String browser = ConfigurationManager.getInstance().getProperty("cloud.browser");
        if (browser != null) {
            capabilities.setCapability("browser", browser);
        }

        String version = ConfigurationManager.getInstance().getProperty("cloud.browser.version");
        if (version != null) {
            capabilities.setCapability("version", version);
        }

        String screenResolution = ConfigurationManager.getInstance().getProperty("cloud.screen.resolution");
        if (screenResolution != null) {
            capabilities.setCapability("screenResolution", screenResolution);
        }

        // Additional capabilities might be needed depending on the cloud service in use.
        // For example, for BrowserStack:
        String browserstackDebug = ConfigurationManager.getInstance().getProperty("browserstack.debug");
        if (browserstackDebug != null) {
            capabilities.setCapability("browserstack.debug", Boolean.parseBoolean(browserstackDebug));
        }

        // If more specific capabilities are needed for different cloud services, add them here.

        return capabilities;
    }
}
