package com.company.automation.driver.management;

import com.company.automation.configuration.ConfigurationManager;

public class WaitConfigurationManager {

    private static final String DEFAULT_WAIT_TIME = "30";

    /**
     * Gets the implicit wait time from configuration or returns a default value.
     * @return the implicit wait time in seconds
     */
    public long getImplicitWait() {
        String waitTime = ConfigurationManager.getInstance().getProperty("implicit.wait");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    /**
     * Gets the explicit wait time from configuration or returns a default value.
     * @return the explicit wait time in seconds
     */
    public long getExplicitWait() {
        String waitTime = ConfigurationManager.getInstance().getProperty("explicit.wait");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    /**
     * Gets the page load time from configuration or returns a default value.
     * @return the page load time in seconds
     */
    public long getPageLoadTime() {
        String waitTime = ConfigurationManager.getInstance().getProperty("page.load.time");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    /**
     * Gets the script timeout from configuration or returns a default value.
     * @return the script timeout in seconds
     */
    public long getScriptTimeout() {
        String waitTime = ConfigurationManager.getInstance().getProperty("script.timeout");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    /**
     * Gets the maximum number of retry attempts from configuration or returns a default value.
     * @return the number of retry attempts
     */
    public int getRetryAttempts() {
        String retryAttempts = ConfigurationManager.getInstance().getProperty("retry.attempts");
        return Integer.parseInt(retryAttempts != null ? retryAttempts : "3");
    }

    /**
     * Gets the retry delay from configuration or returns a default value.
     * @return the retry delay in milliseconds
     */
    public long getRetryDelay() {
        String retryDelay = ConfigurationManager.getInstance().getProperty("retry.delay");
        return Long.parseLong(retryDelay != null ? retryDelay : "5000");
    }

    /**
     * Gets a conditional wait time for a specific condition from configuration or returns a default value.
     * @param condition the name of the condition
     * @return the wait time in seconds for the specific condition
     */
    public long getConditionalWaitTime(String condition) {
        String waitTime = ConfigurationManager.getInstance().getProperty(condition + ".wait.time");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    /**
     * Adjusts the wait times based on the execution environment or test results.
     * You can implement logic here to read the execution times of previous test runs,
     * analyze other factors, and adjust the wait times accordingly.
     */
    public void adjustWaitTimes() {
        // Add your logic here to adjust the wait times based on the execution environment or test results.
        // For example, you could read the execution times of the previous test run from a log file
        // and increase the wait times if the tests were running slower than usual.
    }
}
