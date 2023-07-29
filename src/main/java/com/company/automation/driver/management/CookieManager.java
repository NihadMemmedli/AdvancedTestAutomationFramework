package com.company.automation.driver.management;

import com.company.automation.configuration.ConfigurationManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

/**
 * This class is responsible for handling the cookies in a WebDriver session.
 * It provides methods to add, delete, and retrieve cookies.
 */
public class CookieManager {

    private final WebDriver driver;
    private final ConfigurationManager configurationManager;

    public CookieManager(WebDriver driver) {
        this.driver = driver;
        this.configurationManager = ConfigurationManager.getInstance();
    }

    /**
     * This method is used to add a cookie to the current WebDriver session.
     * @param name the name of the cookie
     * @param value the value of the cookie
     */
    public void addCookie(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    /**
     * This method is used to retrieve a cookie by its name.
     * @param name the name of the cookie
     * @return the cookie
     */
    public Cookie getCookie(String name) {
        return driver.manage().getCookieNamed(name);
    }

    /**
     * This method is used to retrieve all the cookies in the current WebDriver session.
     * @return a set of cookies
     */
    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    /**
     * This method is used to delete a cookie by its name.
     * @param name the name of the cookie
     */
    public void deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
    }

    /**
     * This method is used to delete all the cookies in the current WebDriver session.
     */
    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     * This method is used to load cookies from a predefined location
     * The location is determined by the 'cookie.location' configuration property
     */
    public void loadCookies() {
        // TODO: Implement the logic to load cookies from a file
    }

    /**
     * This method is used to save cookies to a predefined location
     * The location is determined by the 'cookie.location' configuration property
     */
    public void saveCookies() {
        // TODO: Implement the logic to save cookies to a file
    }
}
