package com.company.automation.driver.management;

import com.company.automation.configuration.ConfigurationManager;

public class WaitConfigurationManager {

    private static final String DEFAULT_WAIT_TIME = "30";

    public long getImplicitWait() {
        String waitTime = ConfigurationManager.getInstance().getProperty("implicit.wait");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    public long getExplicitWait() {
        String waitTime = ConfigurationManager.getInstance().getProperty("explicit.wait");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    public long getPageLoadTime() {
        String waitTime = ConfigurationManager.getInstance().getProperty("page.load.time");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    public long getScriptTimeout() {
        String waitTime = ConfigurationManager.getInstance().getProperty("script.timeout");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    public int getRetryAttempts() {
        String retryAttempts = ConfigurationManager.getInstance().getProperty("retry.attempts");
        return Integer.parseInt(retryAttempts != null ? retryAttempts : "3");
    }

    public long getRetryDelay() {
        String retryDelay = ConfigurationManager.getInstance().getProperty("retry.delay");
        return Long.parseLong(retryDelay != null ? retryDelay : "5000");
    }

    public long getConditionalWaitTime(String condition) {
        String waitTime = ConfigurationManager.getInstance().getProperty(condition + ".wait.time");
        return Long.parseLong(waitTime != null ? waitTime : DEFAULT_WAIT_TIME);
    }

    public void adjustWaitTimes() {
        // Add your logic here to adjust the wait times based on the execution environment or test results.
        // For example, you could read the execution times of the previous test run from a log file
        // and increase the wait times if the tests were running slower than usual.
    }
}
