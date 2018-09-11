package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

    /**
     *
     * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        switch (browser) {
            case "ie":
            case "internet explorer":
                return getIEDriver();
            case "firefox":
            case "mozilla":
                return getFirefoxDriver();
            case "chrome":
            case "google":
            default:
                return getChromeDriver();
        }
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.firefox.driver", new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
        return new FirefoxDriver();
    }

    private static WebDriver getIEDriver() {
        System.setProperty("webdriver.ie.driver", new File(BaseScript.class.getResource("/IEDriverServer.exe").getFile()).getPath());
        return new InternetExplorerDriver();
    }

    /**
     * Creates {@link WebDriver} instance with timeout and browser window configurations.
     *
     * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static EventFiringWebDriver getConfiguredDriver() {
        EventFiringWebDriver driver = new EventFiringWebDriver(getDriver());
        driver.register(new WebDriverLogger());

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
