package ru.garik;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Класс по работе с WebDriver
 */
public class SeleniumHandler {

    private static WebDriver driver;

    public SeleniumHandler(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Открывает страницу
     * @param url Требуемая страница
     * @return Возвращает статус выполнения метода
     */
    public boolean openPage(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Ошибка при открытии страницы: " + url);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Возвращает элемент со страницы
     * @param xpath
     * @return
     */
    public WebElement getElement(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Config.getTimeoutSecond());
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return driver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            System.out.println("Ошибка при поиске элемента: " + xpath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Возвращает список элементов
     * @param xpath
     * @return
     */
    public List<WebElement> getElements(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Config.getTimeoutSecond());
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return driver.findElements(By.xpath(xpath));
        } catch (Exception e) {
            System.out.println("Ошибка при поиске элементов: " + xpath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Кликает по заранее найденному элементу
     * @param webElement
     */
    public void click(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception e) {
            System.out.println("Ошибка при клике на элемент: ");
            e.printStackTrace();
        }
    }

    /**
     * ВВодит текст в найденный элемент
     * @param webElement
     * @param Keys
     */
    public void sendKeys(WebElement webElement, String Keys) {
        try {
            webElement.sendKeys(Keys);
        } catch (Exception e) {
            System.out.println("Ошибка при вводе данных: ");
            e.printStackTrace();
        }
    }

    /**
     * Скрипт для авторизации в яндекс
     * @return
     */
    public boolean signInYandex() {
        try {
            sendKeys(getElement("//input[@id='passp-field-login']"), Config.getEMAIL());
            click(getElement("//button[@class='Button2 Button2_size_l Button2_view_action Button2_width_max Button2_type_submit']"));
            sendKeys(getElement("//input[@id='passp-field-passwd']"), Config.getPASSWORD());
            click(getElement("//button[@class='Button2 Button2_size_l Button2_view_action Button2_width_max Button2_type_submit']"));
            return true;
        } catch (Exception e) {
            System.out.println("Залогиниться не получилось: ");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Отправка письма в яндексе
     * @param addressee
     * @param subject
     * @param text
     * @return
     */
    public boolean sendEmail(String addressee, String subject, String text) {
        try {
            if (getURL().contains("mail.yandex.ru")) {
                click(getElement("//span[@class='mail-ComposeButton-Text']"));
                sendKeys(getElement("//div[@class='composeYabbles']"), addressee);
                click(getElement("//div[@class='ContactsSuggestItemDesktop-Name']"));
                sendKeys(getElement("//input[@class='composeTextField ComposeSubject-TextField']"), subject);
                sendKeys(getElement("//div[@class='cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable cke_editable_themed cke_contents_ltr cke_htmlplaceholder']"), text);
                click(getElement("//button[@class='control button2 button2_view_default button2_tone_default button2_size_l button2_theme_action button2_pin_circle-circle ComposeControlPanelButton-Button ComposeControlPanelButton-Button_action']"));
                click(getElement("//a[@class='control link link_theme_normal ComposeDoneScreen-Link']"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Отправить письмо не получилось: ");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Возвращает отправителя из главной страницы почты яндекса
     * @return
     */
    public String getSenderYandex(int i) {
        return getElement("//span[@class='mail-MessageSnippet-FromText'][" + i + "]").getAttribute("title");
    }

    public WebElement getChildElement(String xpath, WebElement parentElement) {
        try {
            return parentElement.findElement(By.xpath(xpath));
        } catch (Exception e) {
            System.out.println("Получить дитя не получилось: ");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Возврашает текущую страницу
     * @return
     */
    public String getURL() {
        return driver.getCurrentUrl();
    }

}
