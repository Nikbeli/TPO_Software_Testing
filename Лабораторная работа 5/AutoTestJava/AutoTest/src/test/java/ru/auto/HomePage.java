package ru.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

public class HomePage extends BasePage {
    private static final String URL = "https://auto.ru/";
    private static final String HISTORY_URL = "https://auto.ru/history/";
    private static final String CATALOG_URL = "https://auto.ru/catalog/cars/";

    // Locators
    private final By inputSearch = By.xpath("//div[contains(@class, 'SearchLineSuggest__input')]//input");
    private final By searchResults = By.xpath("//div[contains(@class,'ListingHead__content')]//h1");
    private final By emptySearch = By.xpath("//div[contains(@class, 'Index')]//div");
    private final By filterPriceFrom = By.xpath("//label[contains(@class, 'TextInput TextInput_size_l TextInput_has-clear TextInput_has-placeholder TextInput_placeholderShowWithValue')]//input");
    private final By filterPriceTo = By.xpath("//label[contains(@class, 'TextInput TextInput_size_l TextInput_has-clear TextInput_has-placeholder TextInput_placeholderShowWithValue')]//input");
    private final By filterButton = By.xpath("//button[contains(@class,'Button2')]");

    private final By catalogSection = By.xpath("//div[contains(@class, 'Index')]");
    private final By catalogFirstPage = By.xpath("//div[contains(@class,'BaseApp__content')]");

    private final By vinInput = By.xpath("//div[contains(@class, 'VinCheckInput3__controls-EZidM')]//input");
    private final By vinCheckButton = By.xpath("//button[contains(@class,'Button2')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Open URL
    public void open() {
        openUrl(URL);
    }

    // Go to History
    public void goToHistory() {
        openUrl(HISTORY_URL);
    }

    // Go to Catalog
    public void goToCatalog() {
        openUrl(CATALOG_URL);
    }

    // Execute Search
    public void executeSearch(String searchText) {
        click(inputSearch, 10);
        type(inputSearch, searchText, 10);
        find(inputSearch).sendKeys(Keys.RETURN);
    }

    // Apply Price Filter
    public void applyPriceFilter(int minPrice, int maxPrice) {
        type(filterPriceFrom, String.valueOf(minPrice), 10);
        type(filterPriceTo, String.valueOf(maxPrice), 10);
        click(filterButton, 10);

        // Задержка перед закрытием браузера (чтобы увидеть результат)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Open Catalog
    public void openCatalog() {
        click(catalogSection, 10);
    }

    // Check VIN
    public void checkVin(String vin) {
        type(vinInput, vin, 5);
        click(vinCheckButton, 5);

        // Задержка перед закрытием браузера (чтобы увидеть результат)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Get First Search Result
    public String getFirstSearchResult() {
        return getText(searchResults, 10);
    }

    // Get Empty Search Result
    public String getEmptySearch() {
        return getText(emptySearch, 10);
    }

    // Get First Catalog Result
    public String getFirstCatalogResult() {
        return getText(catalogFirstPage, 10);
    }
}
