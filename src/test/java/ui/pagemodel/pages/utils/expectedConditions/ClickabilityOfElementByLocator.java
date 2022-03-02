package ui.pagemodel.pages.utils.expectedConditions;

import ui.pagemodel.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

/**
 * ClickabilityOfElementByLocator.
 * @author jsiddiqui
 */
public class ClickabilityOfElementByLocator implements ExpectedCondition<WebElement> {

    private final By locator;

    /**
     * Click on element by locator when its load.
     * @param locator By type parmeter
     */
    public ClickabilityOfElementByLocator(By locator) {
        this.locator = locator;
    }

    @Override
    public WebElement apply(WebDriver webDriver) {

        final Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(Constants.TIMEOUT_SHORT))
                .pollingEvery(Duration.ofMillis(Constants.POLLING_SHORT))
                .ignoring(java.util.NoSuchElementException.class,
                        StaleElementReferenceException.class);

        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (StaleElementReferenceException | NoSuchElementException | ElementNotVisibleException e) {
            return webDriver.findElement(locator);
        } catch (Throwable t) {
            throw new Error(t);
        }
    }

}
