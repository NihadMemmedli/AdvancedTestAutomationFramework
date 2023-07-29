package com.company.automation.driver.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.company.automation.configuration.ConfigurationManager;

import java.util.HashMap;
// import other browser options as needed

public class BrowserConfigurationManager {

    // The instance of the ChromeOptions
    private static ChromeOptions chromeOptions = null;

    // The instance of the FirefoxOptions
    private static FirefoxOptions firefoxOptions = null;

    // Instantiate and configure ChromeOptions
    public static ChromeOptions getChromeOptions() {
        if (chromeOptions == null) {
            chromeOptions = new ChromeOptions();
            // Add any specific Chrome options as per your requirements

            // Load settings from configuration
            String downloadDir = ConfigurationManager.getInstance().getDownloadDir();
            if (downloadDir != null) {
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("download.default_directory", downloadDir);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
            }

            // Add any extensions
            // chromeOptions.addExtensions(new File("/path/to/extension.crx"));
        }
        return chromeOptions;
    }

    // Instantiate and configure FirefoxOptions
    public static FirefoxOptions getFirefoxOptions() {
        if (firefoxOptions == null) {
            firefoxOptions = new FirefoxOptions();
            // Add any specific Firefox options as per your requirements
        }
        return firefoxOptions;
    }

    // similar methods can be created for other browsers.
}
