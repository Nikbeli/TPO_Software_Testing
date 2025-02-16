package ru.auto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class) // Указываем, что это параметризованный тест
public class TestSearch {

    private final String searchText;
    private final boolean isPositive;

    // Конструктор принимает параметры
    public TestSearch(String searchText, boolean isPositive) {
        this.searchText = searchText;
        this.isPositive = isPositive;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"BMW", true}, // Позитивный тест
                {"квкурукру", false},       // Негативный тест
        });
    }

    @Test
    public void testSearch() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.executeSearch(searchText);

        if (isPositive) {
            assert homePage.getFirstSearchResult().contains(searchText);
        } else {
            assert !homePage.getEmptySearch().contains(searchText);
        }

        driver.quit();
    }
}
