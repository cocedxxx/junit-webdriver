import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import static org.assertj.core.api.Assertions.assertThat;

public class WebDriverTest {

    private static WebDriver driver;

    @BeforeClass
    public static void before() {
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        driver = new ChromeDriver();
        driver.get("https://skryabin.com/market/quote.html");
    }

    @Before
    public void beforeScenario() {
        driver.manage().deleteAllCookies();
    }

    @Test
    public void verifyTitle() {
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).isEqualTo("Get a Quote");
    }

    @Test
    public void fieldsTest() {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("johndoe@example.com");
        String actualValue = driver.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        assertThat(actualValue).isEqualTo("johndoe@example.com");
        // Username is a required field
        driver.findElement(By.name("username")).sendKeys("jdoe");
        String userName = driver.findElement(By.name("username")).getAttribute("value");
        assertThat(userName).isEqualTo("jdoe");
    }

    @AfterClass
    public static void after() {
        driver.quit();
    }
}
