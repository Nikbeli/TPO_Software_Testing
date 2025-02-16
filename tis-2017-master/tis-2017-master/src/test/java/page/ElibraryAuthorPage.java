package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ElibraryAuthorPage {
    WebDriver driver;

    @FindBy(id = "surname")
    private WebElement authorNameField;

    @FindBy(xpath = "//*[@id=\"show_param\"]/table[6]/tbody/tr[2]/td[6]/div")
    private WebElement submitSearchForm;

    public ElibraryAuthorPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> findPublications(String authorName, String town) {
        authorNameField.sendKeys(authorName);
        Select townField = new Select(driver.findElement(By.name("town")));
        townField.selectByValue(town);
        submitSearchForm.click();
        driver.findElements(By.xpath("//*[@id=\"restab\"]//td/div/a"))
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("результатов поиска нет"))
                .click();
        List<WebElement> publications = driver.findElements(By.xpath("//*[@id=\"restab\"]//td/a/b"));
        return publications.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
