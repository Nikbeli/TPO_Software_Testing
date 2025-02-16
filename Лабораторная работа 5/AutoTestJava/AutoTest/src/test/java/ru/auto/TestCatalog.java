package ru.auto;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCatalog {

    @Test
    public void testCatalogFirstPage() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.goToCatalog();
        driver.quit();
    }
}
