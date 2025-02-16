using OpenQA.Selenium.Chrome;
using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoTest
{
    public class Tests
    {
        private IWebDriver driver;
        private HomePage homePage;

        [SetUp]
        public void Setup()
        {
            driver = new ChromeDriver();
            driver.Manage().Window.Maximize();
            homePage = new HomePage(driver);
        }

        [TearDown]
        public void TearDown()
        {
            if (driver != null)
            {
                driver.Quit();
                driver.Dispose();
            }
        }

        [Test]
        public void TestCatalogFirstPage()
        {
            homePage.Open();
            homePage.GoToCatalog();
            Thread.Sleep(5000);  // Ждем результата фильтрации
        }

        [Test]
        public void TestFilterByPrice()
        {
            homePage.Open();
            homePage.GoToCatalog();
            homePage.ApplyPriceFilter(90, 150);
            // Можно добавить проверку на успешное применение фильтра
            Thread.Sleep(5000);  // Ждем результата фильтрации
        }
    }
}
