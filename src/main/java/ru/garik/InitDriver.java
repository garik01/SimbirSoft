package ru.garik;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitDriver {

    private static final String FIREFOX_URL_LINUX = System.getProperty("user.dir")+"/src/main/resources/geckodriver";
    private static final String FIREFOX_URL_WINDOWS = System.getProperty("user.dir")+"/src/main/resources/geckodriver.exe";
    private static final String NAME_DRIVER = "webdriver.gecko.driver";

    //TODO: Доделать работу теста на винде
    public static WebDriver init() {
        System.setProperty(NAME_DRIVER, FIREFOX_URL_LINUX);
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

}