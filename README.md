# Advanced Test Automation Framework

This is a customizable Selenium WebDriver automation framework, designed to support multiple browsers, remote execution, and various other features.

## Structure

The framework is divided into different packages for better organization and ease of use:

- `com.company.automation.test`: Contains all the test classes.
- `com.company.automation.pageobjects`: Contains the Page Object classes that abstract the interaction with the web pages.
- `com.company.automation.driver`: Contains classes for managing the WebDriver instances and related configuration.
- `com.company.automation.configuration`: Contains the ConfigurationManager class that manages the application configuration.

## Usage

The framework uses configuration properties that can be set in the `config.properties` file located in the `src/main/resources` directory.

### Local Execution

To execute tests on a local machine, make sure the appropriate WebDriver executable (e.g., `chromedriver` for Chrome, `geckodriver` for Firefox) is in the system's PATH. The configuration parameters `browser.name`, `browser.version`, and `platform.name` can be set in the `config.properties` file.

### Remote Execution

For remote execution, like on a Selenium Grid or cloud-based solution, set the `hub.url` property in the `config.properties` file to the URL of the Selenium Hub. The framework will then automatically use a `RemoteWebDriver` to execute the tests.

### Other Features

The framework also supports other features like:

- Automatic management of cookies
- Customizable wait times with dynamic adjustments
- Support for headless browsers
- Support for mobile browsers
- Support for cloud-based execution (e.g., SauceLabs, BrowserStack)

