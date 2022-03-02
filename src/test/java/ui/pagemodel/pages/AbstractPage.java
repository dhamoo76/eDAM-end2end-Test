package ui.pagemodel.pages;

import ui.pagemodel.pages.dam.utils.AssetMethods;
import ui.pagemodel.pages.utils.AssertionMethods;
import ui.pagemodel.pages.utils.ClickElementsMethods;
import ui.pagemodel.pages.utils.DriverManager;
import ui.pagemodel.pages.utils.DriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pagemodel.pages.utils.InputMethods;
import ui.pagemodel.pages.utils.LocatorMethods;
import ui.pagemodel.pages.utils.NavigateMethods;
import ui.pagemodel.pages.utils.ProgressMethods;

/**
 * AbstractPage class.
 * @author jsiddiqui
 */
public abstract class AbstractPage  {

    private final DriverManager driverManager = new DriverManager();
    private final DriverWait driverWait = new DriverWait(driverManager);

    /**
     * Abstract class constructor.
     */
    protected AbstractPage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    /**
     * Get Web Driver.
     * @return Return drivers
     */
    public WebDriver getDriver() {
        return driverManager.getDriver();
    }

    /**
     * Get Driver Wait.
     * @return Return driver wait
     */
    public DriverWait getDriverWait() {
        return driverWait;
    }

    /**
     * Thread wait.
     * @param time String: time to wait
     * @throws InterruptedException Throws Interrupted Exception
     */
    public void wait(String time) throws InterruptedException {
        Thread.sleep(Integer.parseInt(time));
    }

    /**
     * Find locator Id from locators.properties and return value.
     * @param locatorId : String : locatorId Key to find value
     * @param args : String : Replace %s from locatorId key value
     * @return : String: Return value
     */
    public String getLocator(String locatorId, String... args) {
        final LocatorMethods locatorMethods = new LocatorMethods();
        return locatorMethods.getLocator(locatorId, args);
    }


    /**
     * Navigation Methods class.
     * @return : Return class methods
     */
    public NavigateMethods getNavigationObj() {
        return new NavigateMethods();
    }

    /**
     * Assertion Methods class.
     * @return Return Assertion methods
     */
    public AssertionMethods getAssertionObj() {
        return new AssertionMethods();
    }

    /**
     * Click Element Methods.
     * @return Return Click Element Methods
     */
    public ClickElementsMethods getClickObj() {
        return new ClickElementsMethods();
    }

    /**
     * Click Element Methods.
     * @return Return Click Element Methods
     */
    public InputMethods getInputObj() {
        return new InputMethods();
    }

    /**
     * Progress Methods.
     * @return Return Click Progress methods
     */
    public ProgressMethods getProgressObj() {
        return new ProgressMethods();
    }

    /**
     * Asset Methods.
     * @return Return Click Assets methods
     */
    public AssetMethods getAssetObj() {
        return new AssetMethods();
    }

    /**
     * Get Brand Name Abbreviation.
     * @param brand brand e.g. we
     * @return Return: e.g. west-elm
     */
    public String getBrandNameByAbbreviation(String brand) {
        final LocatorMethods locatorMethods = new LocatorMethods();
        return locatorMethods.getBrandNameByAbbreviation(brand);
    }

}
