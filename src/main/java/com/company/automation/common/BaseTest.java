package com.company.automation.common;


import com.company.automation.driver.DriverManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class BaseTest {
    protected DriverManager driverManager;

    @BeforeSuite
    public void setUp() throws MalformedURLException, URISyntaxException {
        driverManager = new DriverManager();
        driverManager.getDriver();
    }

    @AfterSuite
    public void tearDown() {
        driverManager.quitDriver();
    }
}
