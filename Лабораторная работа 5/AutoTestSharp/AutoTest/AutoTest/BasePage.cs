using OpenQA.Selenium.Support.UI;
using OpenQA.Selenium;
using SeleniumExtras.WaitHelpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoTest
{
    public class BasePage
    {
        protected IWebDriver driver;

        public BasePage(IWebDriver driver)
        {
            this.driver = driver;
        }

        protected IWebElement Find(By locator)
        {
            return driver.FindElement(locator);
        }

        protected void Click(By locator, int timeout)
        {
            WaitUntilElementIsVisible(locator, timeout);
            Find(locator).Click();
        }

        protected void Type(By locator, string text, int timeout)
        {
            WaitUntilElementIsVisible(locator, timeout);
            Find(locator).SendKeys(text);
        }

        protected void WaitUntilElementIsVisible(By locator, int timeout)
        {
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(timeout));
            wait.Until(ExpectedConditions.ElementIsVisible(locator));
        }

        protected void OpenUrl(string url)
        {
            driver.Navigate().GoToUrl(url);
        }

        protected string GetText(By locator, int timeout)
        {
            WaitUntilElementIsVisible(locator, timeout);
            return Find(locator).Text;
        }
    }
}
