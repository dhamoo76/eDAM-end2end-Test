package webConnector;

import factory.Common;
import io.cucumber.core.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to initialize the web driver, based on the browser type in
 * the application.properties file
 */
public class Browser<V> {
    private static final Logger LOGGER = Logger.getLogger(Browser.class.getName());

    public static WebDriver driver = null;
    public static Properties applicationProp = new Properties();
    public SessionId session = null;

    public Browser() {
        try {
            LOGGER.info("Application Logging");
            FileInputStream fis = new FileInputStream("./src/test/config/application.properties");
            applicationProp.load(fis);
            getEnvironmentDetails();
        } catch (IOException e) {
            LOGGER.log(Level.ALL, "Error while reading application properties file ", e.getMessage());
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public static void SwitchTab(int tabNo) {
        ArrayList<String> newTb1 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb1.get(tabNo));
    }

    public static int GetCurrentTabIndex() {
        String currentTab = driver.getWindowHandle();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        int index = tabs.indexOf(currentTab);
        return index;
    }

    public void setUpDriver() {
        String browser = applicationProp.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("headless");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();

                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }

    public void closeDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            saveScreenshotsForScenario(scenario);
        }
        driver.close();
    }

    /**
     * This method will used to take a screenshot while execution
     *
     * @param scenario
     */
    private void saveScreenshotsForScenario(final Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    /**
     * This method will read the value from application properties and store in the
     * static variable
     */
    public void getEnvironmentDetails() {
        try {
            Common.UploadPath = System.getProperty("user.dir") + applicationProp.getProperty("uploadPath");
            Common.Appurl = applicationProp.getProperty("url");
            Common.UserName = applicationProp.getProperty("userName");
            Common.Password = applicationProp.getProperty("password");
            Common.AsyncWaitTime = Long.parseLong(applicationProp.getProperty("asyncTime"));
            Common.ImplicitTime = Long.parseLong(applicationProp.getProperty("implicitTime"));
            Common.ExplicitTime = Long.parseLong(applicationProp.getProperty("explicitTime"));
            Common.Brand = applicationProp.getProperty("brand");
            Common.FileUploadTime = Integer.parseInt(applicationProp.getProperty("fileUploadTime"));
        } catch (Exception e) {
            LOGGER.log(Level.ALL, "Error while configuring common data ", e.getMessage());
        }
    }

    public void waitForPageLoad(int timeout) {
        ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
    }

}
