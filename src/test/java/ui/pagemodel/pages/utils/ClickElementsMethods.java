package ui.pagemodel.pages.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pagemodel.pages.AbstractPage;

/**
 * ClickElementsMethods class.
 * @author jsiddiqui
 */
public class ClickElementsMethods extends AbstractPage {
    private final SelectElementByType selectElementByType = new SelectElementByType();
    private WebElement element;

    /**
     * Method to click on an element.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value from locator.properties
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void click(String locatorType, String locatorId, String... args) {
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        element.click();
    }

    /**
     * Method to forcefully click on an element.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value from locators.properties
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void clickForcefully(String locatorType, String locatorId, String... args) {
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Method to Double click on an element.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String: Location value from locators.properties
     * @param args : String: replace locator value %s
     */
    public void doubleClick(String locatorType, String locatorId, String... args) {
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        Actions action = new Actions(getDriver());
        action.moveToElement(element).doubleClick().perform();
    }

    /**
     * Get Element By Locator.
     * @param locatorType String: locatorType
     * @param locatorId : String locatorId
     * @param args : String... args
     * @return : Return webElement
     */
    public WebElement getElement(String locatorType, String locatorId, String... args) {
        return getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
    }

    /**
     * Method to Double click on an element.
     * @param parentWebElement : WebElement : Parent Element
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String: Location value from locators.properties
     * @param args : String: replace locator value %s
     */
    public void waitUntilPropertiesAppearsAndClick(WebElement parentWebElement, String locatorType, String locatorId, String... args) {

        Actions actionParent = new Actions(getDriver());
        actionParent.moveToElement(parentWebElement).perform();

        WebElement childElement = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));

        Actions action = new Actions(getDriver());
        action.moveToElement(parentWebElement).click(childElement).build().perform();
    }

}
