package runner;

import factory.Common;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import webConnector.Browser;

import java.util.logging.Level;
import java.util.logging.Logger;

@CucumberOptions(glue = {"stepdefs"}, plugin = {
        "html:target/cucumber-reports/cucumber-pretty", "json:target/json-cucumber-reports/default/cukejson.json",
        "testng:target/testng-cucumber-reports/cuketestng.xml"}, features = {"src/test/resources/features/02_HomePage"})
public class Default extends AbstractTestNGCucumberParallelTests {
    private static final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());
    static Browser wc;
    static WebDriver driver;
    static LoginPage log;
    private static long duration;

    @BeforeClass
    public static void before() {
        wc = new Browser();
        wc.setUpDriver();
        driver = wc.getDriver();
        log = new LoginPage(driver);
        driver.get(Common.Appurl);
        log.EnterCredentialAndSelectBrand();
        duration = System.currentTimeMillis();
        LOGGER.log(Level.INFO,"Thread Id  | Scenario Num       | Step Count");
    }

    @AfterClass
    public static void after() {
        wc.getDriver().close();
        duration = System.currentTimeMillis() - duration;
        LOGGER.log(Level.INFO,"DURATION - " + duration);
    }
}
