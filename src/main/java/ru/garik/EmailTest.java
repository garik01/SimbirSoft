package ru.garik;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EmailTest {

    private static final String MAIN_URL = "https://yandex.ru/";
    private static final String MAIL_URL = "https://mail.yandex.ru/";
    private static WebDriver driver;

    public EmailTest(WebDriver driver) {
        this.driver = driver;
    }

    public void startTestYandex() {
        System.out.println("Запуск теста!!!");
        openPage(MAIN_URL);
        click(getElement("//*[text()='Войти']"));
        SignInYandex();
        getElement("//input[@id='text']"); //Строчка для того, чтобы полностью дождаться загрузки страницы
        openPage(MAIL_URL);
        //click(getElement("//div[@class='desk-notif-card__mail-title']"));
        //openPage(MAIL_URL);



//        // TODO: Сделать логгер
//        try {
//            driver.get(MAIN_URL);
//            System.out.println("Открыли страницу");
//
////            WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
////                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
//
//            driver.findElement(By.xpath("//a[@class='home-link desk-notif-card__login-new-item desk-notif-card__login-new-item_enter home-link_black_yes home-link_hover_inherit']")).click();
//            System.out.println("Кликнули по Sing In");
//
//            driver.findElement(By.id("passp-field-login")).sendKeys(Config.getEMAIL());
//            System.out.println("ВВели логин");
//
//            driver.findElement(By.xpath("//button[@class='Button2 Button2_size_l Button2_view_action Button2_width_max Button2_type_submit']")).click();
//            System.out.println("Нажали на продолжить ");
//
//            driver.findElement(By.id("passp-field-passwd")).sendKeys(Config.getPASSWORD());
//            System.out.println("ВВели пароль");
//
//            driver.findElement(By.xpath("//button[@class='Button2 Button2_size_l Button2_view_action Button2_width_max Button2_type_submit']")).click();
//            System.out.println("Нажали на продолжить ");
//
//            //TODO: доделать обработку проверки телефона
//            //needTel(driver, By.xpath("//button[@class='Button2 Button2_size_l Button2_view_pseudo Button2_width_max']"), 3);
//
//            needTel(driver, By.xpath("//div[@class='desk-notif-card__mail-title']"), 3).click();
//            //driver.findElement(By.xpath("//div[@class='desk-notif-card__mail-title']")).click();
//            System.out.println("зашли на почту");
//
//            //TODO: доделать обработку всплывающего сообщения
//            //driver.findElement(By.xpath("//button[@class='mail-Wizard-Close']")).click();
//            //needTel(driver, By.xpath("//button[@class='mail-Wizard-Close']"), 3).click();
//
//            List<WebElement> emals = driver.findElements(By.xpath("//span[@title='Simbirsoft Тестовое задание']"));
//            int count = emals.size();
//            System.out.println("Найдено сообщений: " + count);
//
//            System.out.println("Выполнилось");
//            //driver.close();
//        } catch (Exception e) {
//            System.out.println("Нихуя не выполнилось:\n" + e.getMessage());
//            //driver.close();
//        }


//        openUrl(driver);
//        login(driver);
//        countEmail(driver);
//        sendEmail(driver);
    }

//    private static WebElement needTel(WebDriver driver, By by, int timeoutInSeconds) {
//        WebElement element;
//        try{
//            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
//
//            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
//
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //reset implicitlyWait
//            return element; //return the element
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
////        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
////        wait.until(ExpectedConditions.presenceOfElementLocated(by)); //throws a timeout exception if element not present after waiting <timeoutInSeconds> seconds
////        return driver.findElement(by);
//    }

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

    public WebElement getElementForKeys(String id) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Config.getTimeoutSecond());
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
            return driver.findElement(By.id(id));
        } catch (Exception e) {
            System.out.println("Ошибка при поиске элемента: " + id);
            e.printStackTrace();
            return null;
        }
    }

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

    public void click(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception e) {
            System.out.println("Ошибка при клике на элемент: ");
            e.printStackTrace();
        }
    }

    public void sendKeys(WebElement webElement, String Keys) {
        try {
            webElement.sendKeys(Keys);
        } catch (Exception e) {
            System.out.println("Ошибка при вводе данных: ");
            e.printStackTrace();
        }
    }

    public boolean SignInYandex() {
        try {
            sendKeys(getElement("//input[@id='passp-field-login']"), Config.getEMAIL());
            click(getElement("//button[@class='Button2 Button2_size_l Button2_view_action Button2_width_max Button2_type_submit']"));
            sendKeys(getElement("//input[@id='passp-field-passwd']"), Config.getPASSWORD());
            click(getElement("//button[@class='Button2 Button2_size_l Button2_view_action Button2_width_max Button2_type_submit']"));
        } catch (Exception e) {
            System.out.println("Залогиниться не получилось: ");
            e.printStackTrace();
        }
        return false;
    }

}
