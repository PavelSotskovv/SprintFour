package Pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Clock;
import java.time.Duration;

//Класс страницы "Для кого самокат"
public class UserDataFillingForm {
    private WebDriver driver;

    private By firstNameField = By.cssSelector("input[placeholder='* Имя']"); //Локатор для поля "Имя"


    private By lastNameField = By.cssSelector("input[placeholder='* Фамилия']"); //Локатор для поля "Фамилия"

    private By addressToDeliveryField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']"); //Локатор для поля "Адрес: куда привезти заказ"


    private By metroStationDropDown = By.className("select-search__input"); //Локатор для дропдауна выбора станции метро


    private By metroStationFirstItem = By.cssSelector("button[tabindex='-1'][value='9']" +
            "[class='Order_SelectOption__82bhS select-search__option']"); //Локатор для клика по первому элементу выпадающего списка

    //Локатор для поля "Телефон: на него позвонит курьер"
    private By userPhoneNumberField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");


    private By nextButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') " +
            "and contains(@class, 'Button_Middle__1CSJM')]"); //Локатор на кнопку "Далее"


    //Конструктор класса
    private By pageOpened = By.className("Order_Header__BZXOb");

    // локатор на то, что страниц "Для кого самокат" открыта

    public void isPageOpened () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement checkStatusTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(pageOpened));
        Assert.assertTrue("Заголовок для Кого самокат не виден", checkStatusTextElement.isDisplayed());
    }
//    public WebDriverWait(WebDriver driver, Duration timeout) {
//        this(
//                driver,
//                timeout,
//                Duration.ofMillis(DEFAULT_SLEEP_TIMEOUT),
//                Clock.systemDefaultZone(),
//                Sleeper.SYSTEM_SLEEPER);
//    }

    //Метод для заполнения поля "Имя"
    public void setFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    //Метод для заполнения поля "Фамилия"
    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    //Метод для заполнения поля "Адрес: куда привезти самокат"
    public void setDeliveryAddress(String deliveryAddress) {
        driver.findElement(addressToDeliveryField).sendKeys(deliveryAddress);
    }

    private String locatorOfIncorrectMessage = "//div[contains(@class, 'Input_ErrorMessage__3HvIb')" +
            " and text()='Введите%s"; //Локатор для текста ошибки заполнения полей (опционально, 3 задание доп)

    //Метод для выбора станции метро из списка
    public void selectMetroStation() {
        driver.findElement(metroStationDropDown).click(); //клик на поле
        WebElement element = driver.findElement(metroStationDropDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element); //скролл до станции
        driver.findElement(metroStationFirstItem).click(); //клик по станции
    }

    //Метод для заполнения поля "Телефон:на него позвонит курьер"
    public void setUserPhoneNumber(String userPhoneNumber) {
        driver.findElement(userPhoneNumberField).sendKeys(userPhoneNumber);
    }

    //Метод нажатия кнопки "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Метод для заполнения всех полей
    public void allFieldsFill(String firstName, String lastName, String address, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setDeliveryAddress(address);
        selectMetroStation();
        setUserPhoneNumber(phoneNumber);
    }
}