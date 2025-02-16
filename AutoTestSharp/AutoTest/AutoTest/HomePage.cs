using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoTest
{
    public class HomePage : BasePage
    {
        private const string URL = "https://auto.ru/";
        private const string HISTORY_URL = "https://auto.ru/history/";
        private const string CATALOG_URL = "https://auto.ru/catalog/cars/";

        // Locators
        private readonly By inputSearch = By.XPath("//div[contains(@class, 'SearchLineSuggest__input')]//input");
        private readonly By searchResults = By.XPath("//div[contains(@class,'ListingHead__content')]//h1");
        private readonly By emptySearch = By.XPath("//div[contains(@class, 'Index')]//div");
        private readonly By filterPriceFrom = By.XPath("//label[contains(@class, 'TextInput TextInput_size_l TextInput_has-clear TextInput_has-placeholder TextInput_placeholderShowWithValue')]//input");
        private readonly By filterPriceTo = By.XPath("//label[contains(@class, 'TextInput TextInput_size_l TextInput_has-clear TextInput_has-placeholder TextInput_placeholderShowWithValue')]//input");
        private readonly By filterButton = By.XPath("//button[contains(@class,'Button2')]");
        private readonly By catalogSection = By.XPath("//div[contains(@class, 'Index')]");
        private readonly By catalogFirstPage = By.XPath("//div[contains(@class,'BaseApp__content')]");
        private readonly By vinInput = By.XPath("//div[contains(@class, 'VinCheckInput3__controls-EZidM')]//input");
        private readonly By vinCheckButton = By.XPath("//button[contains(@class,'Button2')]");

        public HomePage(IWebDriver driver) : base(driver) { }

        public void Open()
        {
            OpenUrl(URL);
        }

        public void GoToHistory()
        {
            OpenUrl(HISTORY_URL);
        }

        public void GoToCatalog()
        {
            OpenUrl(CATALOG_URL);
        }

        public void ExecuteSearch(string searchText)
        {
            Click(inputSearch, 10);
            Type(inputSearch, searchText, 10);
            Find(inputSearch).SendKeys(Keys.Enter);
        }

        public void ApplyPriceFilter(int minPrice, int maxPrice)
        {
            Type(filterPriceFrom, minPrice.ToString(), 10);
            Type(filterPriceTo, maxPrice.ToString(), 10);
            Click(filterButton, 10);
            Thread.Sleep(5000);  // Ждем результата фильтрации
        }

        public void OpenCatalog()
        {
            Click(catalogSection, 10);
        }

        public void CheckVin(string vin)
        {
            Type(vinInput, vin, 5);
            Click(vinCheckButton, 5);
            Thread.Sleep(5000);
        }

        public string GetFirstSearchResult()
        {
            return GetText(searchResults, 10);
        }

        public string GetEmptySearch()
        {
            return GetText(emptySearch, 10);
        }

        public string GetFirstCatalogResult()
        {
            return GetText(catalogFirstPage, 10);
        }
    }
}
