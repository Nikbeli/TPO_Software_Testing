package ru.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void click(By locator, int timeout) {
        waitUntilElementIsVisible(locator, timeout);
        find(locator).click();
    }

    protected void type(By locator, String text, int timeout) {
        waitUntilElementIsVisible(locator, timeout);
        find(locator).sendKeys(text);
    }

    protected void waitUntilElementIsVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected String getText(By locator, int timeout) {
        waitUntilElementIsVisible(locator, timeout);
        return find(locator).getText();
    }
}
