package ui.pagemodel.pages.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pagemodel.pages.AbstractPage;

/**
 * Progress methods class.
 * Wait for elements to be displayed
 */
public class ProgressMethods extends AbstractPage {

    private final SelectElementByType selectElementByType = new SelectElementByType();

    /**
     * Method to Explicitly wait for element to be displayed.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param duration   : String : Time to wait for element to be displayed
     * @param locatorId : String : Locator key from locators.properties
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void waitForElementToDisplay(String locatorType, String duration, String locatorId, String... args) {
        By byEle = selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args));
        WebDriverWait wait = (new WebDriverWait(getDriver(), Integer.parseInt(duration) * 1000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
    }

    /**
     * Method to Explicitly wait for element to be enabled=click.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param duration   : String : Time to wait for element to be clickable
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void waitForElementToClick(String locatorType, String duration, String locatorId, String... args) {
        By byEle = selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args));
        WebDriverWait wait = (new WebDriverWait(getDriver(), Integer.parseInt(duration) * 1000L));
        wait.until(ExpectedConditions.elementToBeClickable(byEle));
    }

}
