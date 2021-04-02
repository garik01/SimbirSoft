package ru.garik;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Tests {

    private static final String MAIN_URL_YANDEX = "https://yandex.ru/";
    private static final String MAIL_URL_YANDEX = "https://mail.yandex.ru/";

    public static void startTestYandex(WebDriver driver) {
        SeleniumHandler selenium = new SeleniumHandler(driver);
        // TODO: Сделать логгер
        System.out.println("Запуск теста!!!");
        selenium.openPage(MAIN_URL_YANDEX);
        selenium.click(selenium.getElement("//*[text()='Войти']"));
        selenium.signInYandex();
        //TODO: доделать обработку проверки телефона
        //needTel(driver, By.xpath("//button[@class='Button2 Button2_size_l Button2_view_pseudo Button2_width_max']"), 3);
        selenium.getElement("//input[@id='text']"); //Строчка для того, чтобы полностью дождаться загрузки страницы
        selenium.openPage(MAIL_URL_YANDEX);
        //TODO: доделать обработку всплывающего сообщения
        //driver.findElement(By.xpath("//button[@class='mail-Wizard-Close']")).click();
        //needTel(driver, By.xpath("//button[@class='mail-Wizard-Close']"), 3).click();
        //List<WebElement> emails = selenium.getElements("//span[contains(text(), 'Simbirsoft Тестовое задание')]");
        List<WebElement> emails = selenium.getElements("//div[@class='mail-MessageSnippet-Content']//span[@title='Simbirsoft Тестовое задание']");
        int count = emails.size();
        System.out.println("Найдено сообщений: " + count);
        for (int i = 0; i < count; i++) {
            selenium.click(selenium.getElement("//span[@title='Simbirsoft Тестовое задание']"));
            String sender = selenium.getElement("//span[@class='mail-Message-Sender-Email mail-ui-HoverLink-Content']").getText();
            selenium.click(selenium.getElement("//span[@class='mail-Toolbar-Item-Text js-toolbar-item-title js-toolbar-item-title-delete']"));
            selenium.sendEmail(sender,"Simbirsoft Тестовое задание. (Demidov)","count: " + count);
        }
    }

    public static void startTestGoogle(WebDriver driver) {
        //TODO: Сделать тест по гуглу
    }

}
