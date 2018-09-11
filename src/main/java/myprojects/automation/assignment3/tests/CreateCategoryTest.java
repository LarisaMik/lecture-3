package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import myprojects.automation.assignment3.GeneralActions;
import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.UUID;

public class CreateCategoryTest extends BaseScript {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = getConfiguredDriver();
        GeneralActions actions = new GeneralActions(driver);
        actions.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw ");
        String category = UUID.randomUUID().toString();
        actions.createCategory(category);

        Asserts.check(driver.findElement(By.className("alert-success")).isDisplayed(), "Category not created");

        driver.findElement(By.name("categoryFilter_name")).sendKeys(category);
        driver.findElement(By.xpath("//button[@id='submitFilterButtoncategory']")).click();

        actions.waitForContentLoad("//td[@class='pointer']");

        List<WebElement> elements = driver.findElements(By.xpath("//td[@class='pointer']"));
        Asserts.notNull(elements, "Elements with xpath //td[@class='pointer'] not found");
        Asserts.notNull(elements.get(0), "Elements with xpath //td[@class='pointer'] not found");
        Asserts.check(category.equalsIgnoreCase(elements.get(0).getText()), "Category " + category + " not found");
    }
}
