package ru.auto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class) // Указываем, что это параметризованный тест
public class TestVinCheck {

    private final String vinCode; // Параметр VIN-кода

    public TestVinCheck(String vinCode) {
        this.vinCode = vinCode;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> vinCodes() {
        return Arrays.asList(new Object[][]{
                {"K804РТ73"}, // Пример VIN-кода
        });
    }

    @Test
    public void testVinCheck() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.goToHistory();
        homePage.checkVin(vinCode);

        // TODO: Добавить проверки результата VIN-кода
        // Например, assert homePage.getVinCheckResult().contains(expectedResult);

        driver.quit();
    }
}
