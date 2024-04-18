package ru.netology.HomeWorkCardOrder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCardOrderNegative {
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
    public void emptyNameInput() {
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79500000000");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        Assertions.assertEquals("Поле обязательно для заполнения",
                driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim());
        Assertions.assertTrue(driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).isDisplayed());

    }
    @Test
    public void noCorrectPhoneInput() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сергей Семенович Милош-Барош");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("Пвавчр");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim());
 //       assertTrue(driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).isDisplayed());

    }
    @Test
    public void emptyPhoneInput() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сергей Семенович Милош-Барош");
//        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Пвавчр");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        Assertions.assertEquals("Поле обязательно для заполнения",
                driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim());
        //       assertTrue(driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).isDisplayed());

    }
    @Test
    public void noCheckedCheckBox() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сергей Семенович Милош-Барош");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79500000000");
 //       driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
       Assertions.assertTrue(driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).isDisplayed());
        //       assertTrue(driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).isDisplayed());

    }
}
