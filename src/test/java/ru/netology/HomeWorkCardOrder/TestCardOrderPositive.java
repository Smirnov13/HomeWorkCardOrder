package ru.netology.HomeWorkCardOrder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCardOrderPositive {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach

    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void positiveForm() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сергей Сергеевич Милош-Барош");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79500000000");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        var actualTextElement = driver.findElement(By.cssSelector("[data-test-id=order-success]"));
        var actualText =actualTextElement.getText().trim();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
        Assertions.assertTrue(actualTextElement.isDisplayed());
    }


}
