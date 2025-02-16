package ru.auto;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestFilter {

    @Test
    public void testFilterByPrice() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.goToCatalog();
        homePage.applyPriceFilter(90, 150);
        // Add assertions for checking the filter
        driver.quit();
    }
}
