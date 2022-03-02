package ui.pagemodel.pages.utils;

import ui.pagemodel.constants.Constants;
import ui.pagemodel.pages.utils.expectedConditions.ClickabilityOfElement;
import ui.pagemodel.pages.utils.expectedConditions.ClickabilityOfElementByLocator;
import ui.pagemodel.pages.utils.expectedConditions.InvisibilityOfElement;
import ui.pagemodel.pages.utils.expectedConditions.InvisibilityOfElementByLocator;
import ui.pagemodel.pages.utils.expectedConditions.VisibilityOfElement;
import ui.pagemodel.pages.utils.expectedConditions.VisibilityOfElementByLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * DriverWait class, waitForElement to load and perform actions.
 *
 * @author jsiddiqui
 */
public class DriverWait {
    private final Logger logger = LoggerFactory.getLogger(DriverWait.class);

    private final DriverManager driverManager;

    /**
     * DriverWait Constructor.
     *
     * @param driverManager Parameter driverManager
     */
    public DriverWait(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    /**
     * Wait until angular ready.
     */
    public void waitForAngular() {
        waitUntilAngularReady();
    }

    /**
     * Wait for element to load.
     *
     * @param element WebElement: parameter
     * @throws NoSuchFieldException Throws NoSuchFieldException
     */
    public void waitForElementToLoad(WebElement element) throws NoSuchFieldException {
        waitForAngular();
        waitForElementVisible(element);
        waitForElementClickable(element);
    }

    /**
     * Wait for Element to load method.
     * @param locator : Parameter 'By' locator
     * @throws NoSuchFieldException : Throws Error NoSuchFieldException
     */
    public void waitForElementToLoad(By locator) throws NoSuchFieldException {
        waitForAngular();
        waitForElementVisible(locator);
        waitForElementClickable(locator);
    }

    /**
     * wait for element visible by element.
     * @param element : WebElement parameter
     */
    private void waitForElementVisible(WebElement element) {
        try {
            waitLong().until(new VisibilityOfElement(element));
        } catch (Exception ex) {
            logger.error("Error while wait for element visible {}", ex.getMessage());
        }
    }

    /**
     * wait for element visible by locator.
     * @param locator : parameter type By
     */
    private void waitForElementVisible(By locator) {
        try {
            waitLong().until(new VisibilityOfElementByLocator(locator));
        } catch (Exception ex) {
            logger.error("Error while wait for element visible {}", ex.getMessage());
        }
    }

    /**
     * wait for element Invisible by locator.
     * @param locator : parameter type By
     */
    private void waitForElementInVisible(By locator) {
        try {
            new InvisibilityOfElementByLocator(locator);
        } catch (Exception ex) {
            logger.error("Error while wait for element visible {}", ex.getMessage());
        }
    }

    /**
     * wait for element Invisible by locator.
     * @param element : WebElement parameter
     */
    private void waitForElementInVisible(WebElement element) {
        try {
            new InvisibilityOfElement(element);
        } catch (Exception ex) {
            logger.error("Error while wait for element invisible {}", ex.getMessage());
        }
    }

    /**
     * wait for element clickable by element.
     * @param element : WebElement parameter
     * @throws NoSuchFieldException : No such field exception
     */
    private void waitForElementClickable(WebElement element) throws NoSuchFieldException {
        try {
            new ClickabilityOfElement(element);
        } catch (Exception t) {
            throw new NoSuchFieldException("could not interact with the element " + element);
        }
    }

    /**
     * wait for element clickable by locator.
     * @param locator : Locator type By
     * @throws NoSuchFieldException : No such field exception
     */
    private void waitForElementClickable(By locator) throws NoSuchFieldException {
        try {
            new ClickabilityOfElementByLocator(locator);
        } catch (Exception t) {
            throw new NoSuchFieldException("could not interact with the element by locator " + locator);
        }
    }

    /**
     * Wait long method.
     * @return : FluentWait
     */
    public Wait<WebDriver> waitLong() {
        waitForJQueryLoad();
        waitUntilJSReady();
        return new FluentWait<>(driverManager.getDriver())
                .withTimeout(Duration.ofSeconds(Constants.TIMEOUT_LONG))
                .pollingEvery(Duration.ofMillis(Constants.POLLING_LONG))
                .ignoring(java.util.NoSuchElementException.class, StaleElementReferenceException.class);
    }
    /**
     * Wait short method.
     * @return : FluentWait
     */
    public Wait<WebDriver> waitShort() {
        waitForJQueryLoad();
        waitUntilJSReady();
        return new FluentWait<>(driverManager.getDriver())
                .withTimeout(Duration.ofSeconds(Constants.TIMEOUT_SHORT))
                .pollingEvery(Duration.ofMillis(Constants.POLLING_SHORT))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }

    /**
     * Wait until angular ready.
     */
    private void waitUntilAngularReady() {

        final Boolean angularUnDefined = (Boolean) ((JavascriptExecutor) driverManager.getDriver()).executeScript("return window.angular === undefined");

        if (!angularUnDefined) {
            Boolean angularInjectorUnDefined =
                    (Boolean) ((JavascriptExecutor) driverManager.getDriver()).executeScript("return angular.element(document).injector() === undefined");
            if (!angularInjectorUnDefined) {
                waitForAngularLoad();
                waitUntilJSReady();
                waitForJQueryLoad();
            }
        }
    }

    /**
     * Wait for angular load.
     */
    private void waitForAngularLoad() {

        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        final ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(
                ((JavascriptExecutor) driverManager.getDriver()).executeScript(angularReadyScript).toString());

        boolean angularReady = Boolean
                .parseBoolean(((JavascriptExecutor) driverManager.getDriver()).executeScript(angularReadyScript).toString());

        if (!angularReady) {
            waitLong().until(angularLoad);
        }
    }

    /**
     * Wait until JavaScript ready.
     */
    private void waitUntilJSReady() {
        final ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driverManager.getDriver())
                .executeScript("return document.readyState")
                .toString()
                .equals("complete");

        boolean jsReady = ((JavascriptExecutor) driverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        if (!jsReady) {
            waitLong().until(jsLoad);
        }
    }

    /**
     * Wait for jquery Load.
     */
    private void waitForJQueryLoad() {
        final ExpectedCondition<Boolean> jQueryLoad = driver -> (
                (Long) ((JavascriptExecutor) driverManager.getDriver()).executeScript("return jQuery.active") == 0);

        boolean jqueryReady = (Boolean) ((JavascriptExecutor) driverManager.getDriver()).executeScript("return jQuery.active==0");

        if (!jqueryReady) {
            waitLong().until(jQueryLoad);
        }
    }


    /**
     * Explicit Wait.
     * @param waitInMiliseconds : Int: wait in milisecond e.g. 5000
     */
    public void explicitWait(int waitInMiliseconds) {
        try {
            Thread.sleep(waitInMiliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
