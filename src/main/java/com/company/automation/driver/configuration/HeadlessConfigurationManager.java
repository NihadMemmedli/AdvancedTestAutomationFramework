package com.company.automation.driver.configuration;

import com.company.automation.configuration.ConfigurationManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * HeadlessConfigurationManager is a configuration class responsible for managing the settings
 * and capabilities for headless browsers, which are web browsers without a GUI.

 * Headless browsers are primarily used for automating tasks and for testing websites, as they can
 * speed up the execution time and run tests in environments without a display. This can be especially
 * useful in continuous integration/continuous deployment (CI/CD) setups.
 */
public class HeadlessConfigurationManager {

    /**
     * Sets up and returns the headless options for Chrome.
     *
     * @return ChromeOptions for headless mode
     */
    public ChromeOptions getHeadlessChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        boolean isHeadless = Boolean.parseBoolean(ConfigurationManager.getInstance().getProperty("headless"));
        options.setHeadless(isHeadless);

        String windowSize = ConfigurationManager.getInstance().getProperty("window.size");
        options.addArguments("window-size=" + (windowSize != null ? windowSize : "1920,1080"));

        return options;
    }

    /**
     * Sets up and returns the headless options for Firefox.
     *
     * @return FirefoxOptions for headless mode
     */
    public FirefoxOptions getHeadlessFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        boolean isHeadless = Boolean.parseBoolean(ConfigurationManager.getInstance().getProperty("headless"));
        options.setHeadless(isHeadless);

        String windowSize = ConfigurationManager.getInstance().getProperty("window.size");
        options.addArguments("window-size=" + (windowSize != null ? windowSize : "1920,1080"));

        return options;
    }
}
