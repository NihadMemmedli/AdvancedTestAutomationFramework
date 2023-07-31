package com.company.automation.driver;

import com.company.automation.configuration.ConfigurationManager;
import com.company.automation.driver.configuration.*;
import com.company.automation.driver.management.WaitConfigurationManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final ConfigurationManager configManager = ConfigurationManager.getInstance();
    private final CloudConfigurationManager cloudConfigManager = new CloudConfigurationManager();
    private final HeadlessConfigurationManager headlessConfigManager = new HeadlessConfigurationManager();
    private final String runMode = configManager.getProperty("run.mode");
    private final String hubUrl = configManager.getProperty("hub.url");
    private final String gridUrl = configManager.getProperty("cloud.url");


    public WebDriver getDriver() throws URISyntaxException, MalformedURLException {
        if (driver.get() == null) {
            WebDriver webDriver = null;
            DesiredCapabilities capabilities = new DesiredCapabilities();
            String browser = configManager.getProperty("browser");

            if (Boolean.parseBoolean(configManager.getProperty("headless"))) {
                if ("chrome".equalsIgnoreCase(browser)) {
                    ChromeOptions chromeOptions = headlessConfigManager.getHeadlessChromeOptions();
//                    chromeOptions.setHeadless(true);
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    System.out.println("Chrome Options: " + chromeOptions.toString());
                } else if ("firefox".equalsIgnoreCase(browser)) {
                    FirefoxOptions firefoxOptions = headlessConfigManager.getHeadlessFirefoxOptions();
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                }
            }

            switch (runMode.toLowerCase()) {
                case "cloud":
                    DesiredCapabilities cloudCapabilities = cloudConfigManager.getCloudCapabilities();
                    webDriver = new RemoteWebDriver(new URI(hubUrl).toURL(), cloudCapabilities);
                    break;
                case "grid":
                    DesiredCapabilities gridCapabilities = new DesiredCapabilities();
                    webDriver = new RemoteWebDriver(new URI(gridUrl).toURL(), gridCapabilities);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver(new FirefoxOptions().merge(capabilities));
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(new ChromeOptions().merge(capabilities));
                    break;
            }

            // Apply custom wait times configuration
            WaitConfigurationManager waitConfigManager = new WaitConfigurationManager();
            webDriver.manage().timeouts().implicitlyWait(waitConfigManager.getImplicitWait(), TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(waitConfigManager.getPageLoadTime(), TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(waitConfigManager.getScriptTimeout(), TimeUnit.SECONDS);

            driver.set(webDriver);
        }

        return driver.get();
    }
    /**
     * Quits the WebDriver instance, closing all associated windows.
     */
    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Remove the driver from the thread-local storage
        }
    }

    /**
     * Navigates to the specified URL using the WebDriver instance.
     *
     * @param url the URL to navigate to
     */
    public void navigateTo(String url) {
        if (driver.get() != null) {
            driver.get().navigate().to(url);
        }
    }

    /**
     * Takes a screenshot and saves it to the specified file path.
     *
     * @param filePath the file path where the screenshot should be saved
     * @throws IOException if an I/O error occurs while saving the screenshot
     */
    public void takeScreenshot(String filePath) throws IOException {
        if (driver.get() != null) {
            File screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
        }
    }

    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        if (driver.get() != null) {
            driver.get().navigate().refresh();
        }
    }
}
