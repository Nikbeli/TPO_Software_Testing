using OpenQA.Selenium.Chrome;
using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoTest
{
    public class ParametrizedTests
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

        [TestCase("BMW", true)]
        [TestCase("квкурукру", false)]
        public void TestSearch(string searchText, bool isPositive)
        {
            homePage.Open();
            homePage.ExecuteSearch(searchText);

            if (isPositive)
            {
                Assert.That(homePage.GetFirstSearchResult().Contains(searchText), Is.True);
            }
            else
            {
                Assert.That(homePage.GetEmptySearch().Contains(searchText), Is.False);
            }

            Thread.Sleep(5000);  // Ждем результата фильтрации
        }

        [TestCase("K804РТ73")]
        public void TestVinCheck(string vinCode)
        {
            homePage.Open();
            homePage.GoToHistory();
            homePage.CheckVin(vinCode);
            // TODO: Добавить проверку результата VIN-кода
        }
    }
}
