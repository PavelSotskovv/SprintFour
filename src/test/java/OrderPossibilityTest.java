import pageobject.AboutRentPage;
import pageobject.HomePage;
import pageobject.UserDataFillingForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;



public class OrderPossibilityTest {

    private WebDriver driver; //поле вебдрайвера
    private HomePage homePage; //поле объявления класса домашней страницы
    private UserDataFillingForm userDataFillingForm; //поле объявления класса страницы "Для кого самокат"
    private AboutRentPage aboutRentPage; //поле объявления класса "Про аренду"

    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru";

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.get(SCOOTER_URL); //URL "Самоката"
        homePage = new HomePage(driver); //инициализация объекта домашней страницы
        userDataFillingForm = new UserDataFillingForm(driver); //инициализация объекта страницы "Для кого самокат"
        aboutRentPage = new AboutRentPage(driver); //инициализация объекта страницы "Про аренду"
        homePage.cookieApproving(); // Принимаем куки
        driver.manage().window().maximize();  // Увеличение экрана
    }

    @Test
    public void testOrderPossibilityHeaderButton() { //Метод заполнения всех форм
        homePage.clickOrderButtonFromHeader(); //Нажимаем на кнопку "Заказать" в шапке главной страницы
        //Передаем на страницу "Для кого самокат" требуемые аргументы. Метод также выбирает поле "Станция метро"
        userDataFillingForm.allFieldsFill("Геннадий", "Антонов", "Римская 22", "+78005553535");
        userDataFillingForm.clickNextButton();
        //Передаем на страницу "Про аренду" требуемые аргументы. Метод также осуществляет выбор даты, срока аренды и клики на чекбокс "Цвет самоката", а также на кнопки "Заказать" и "Да"
        aboutRentPage.fillDateField("30.01.2024", "Колеса должны быть идеально черными");
        //Проверка, что заказ был принят, методом поиска отображения кнопки "Статус заказа"
        assertTrue("Заголовок заказ оформлен не видим", aboutRentPage.checkStatusButton().isDisplayed());
    }

    @Test
    public void testOrderPossibilityHeaderButtonSecond() { //Метод заполнения всех форм
        homePage.clickOrderButtonFromHeader(); //Нажимаем на кнопку "Заказать" в шапке главной страницы
        //Передаем на страницу "Для кого самокат" требуемые аргументы. Метод также выбирает поле "Станция метро"
        userDataFillingForm.allFieldsFill("Евпатий", "Коловрат", "Рязань", "+78005553535");
        userDataFillingForm.clickNextButton();
        //Передаем на страницу "Про аренду" требуемые аргументы. Метод также осуществляет выбор даты, срока аренды и клики на чекбокс "Цвет самоката", а также на кнопки "Заказать" и "Да"
        aboutRentPage.fillDateField("30.06.2024", "Колеса должны быть");
        //Проверка, что заказ был принят, методом поиска отображения кнопки "Статус заказа"
        assertTrue("Заголовок заказ оформлен не видим", aboutRentPage.checkStatusButton().isDisplayed());
    }


        @Test
        public void testOrderPossibilityMiddleButton () {
        homePage.clickOrderButtonFromMiddle(); //Нажимаем на кнопку "Заказать" в центре страницы
        UserDataFillingForm.isPageOpened();
    }


        @After
        public void tearDown () {
        driver.quit(); //Закрываем браузер
    }
    }


