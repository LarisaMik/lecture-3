package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.Properties;
import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        driver.get(Properties.getBaseAdminUrl());
        WebElement loginField = driver.findElement(By.id("email"));
        loginField.sendKeys("webinar.test@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName) {
        Actions action = new Actions(driver) ;
        WebElement element = driver.findElement(By.xpath("//li[@data-submenu='9']"));
        action.moveToElement(element).build().perform();
        waitForContentLoad("//li[@data-submenu='11']");
        driver.findElement(By.xpath("//li[@data-submenu='11']")).click();
        driver.findElement(By.id("page-header-desc-category-new_category")).click();
        driver.findElement(By.name("name_1")).sendKeys(categoryName);
        driver.findElement(By.id("category_form_submit_btn")).click();
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad(String xpath) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));

    }

}
