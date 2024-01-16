import pageobject.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class DropdownTest {

    private WebDriver driver; //поле вебдрайвера
    private HomePage homePage; //поле объявления класса домашней страницы
    private int index; //поле переменной-хвоста ссылки на локатор дропдауна
    private String expected; //поле с текстом ожидаемого результата (текст дропдауна)

    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru";

    public DropdownTest(String expected, int index){ //конструктор с параметрами ОР и индекса с их инициализацией
    this.expected = expected;
    this.index = index;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials(){ //Здесь мы передаем параметры ОР с текстом и индекса хвостов локатора на дропдауны
        return new Object[][] {
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.",3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.get(SCOOTER_URL); //URL "Самоката"
        homePage = new HomePage(driver); //инициализация объекта домашней страницы
        homePage.cookieApproving(); //принимаем куки
        driver.manage().window().maximize();  // Увеличиваем экран
    }

    @Test
    public void dropdownContentTest(){
    homePage.clickOnAccordion(index); //кликаем по дропдауну, индекс которого передаем в его локатор
    assertEquals(expected, homePage.getDropdownTextValue(index)); //выполняем сравнение ОР с полученным текстовым значением поля открытого дропдауна по текущему индексу
    }

    @After
    public void tearDown() {
        driver.quit();
    } // Закрываем браузер
}
