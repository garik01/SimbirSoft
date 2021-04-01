package ru.garik;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitDriver {

    private static final String FIREFOX_URL = System.getProperty("user.dir")+"/src/main/resources/geckodriver";
    private static final String NAME_DRIVER = "webdriver.gecko.driver";

    public static WebDriver init() {
        System.setProperty(NAME_DRIVER, FIREFOX_URL);
        WebDriver driver = new FirefoxDriver();
        return driver;
    }



}
