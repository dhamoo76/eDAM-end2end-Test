package ui.pagemodel.pages.utils.expectedConditions;

import ui.pagemodel.constants.Constants;
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
 * ClickabilityOfElement Wait for element to load to perform actions.
 * @author jsiddiqui
 */
public class ClickabilityOfElement implements ExpectedCondition<WebElement> {

    private final WebElement element;

    /**
     * Click on element until element load.
     * @param element WebElement parameter.
     */
    public ClickabilityOfElement(WebElement element) {
        this.element = element;
    }

    @Override
    public WebElement apply(WebDriver webDriver) {

        final Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(Constants.TIMEOUT_SHORT))
                .pollingEvery(Duration.ofMillis(Constants.POLLING_SHORT))
                .ignoring(java.util.NoSuchElementException.class,
                        StaleElementReferenceException.class);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException | NoSuchElementException | ElementNotVisibleException e) {
            return element;
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
}