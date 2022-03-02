package ui.pagemodel.pages.utils.expectedConditions;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * VisibilityOfElement.
 * @author jsiddiqui
 */
public class VisibilityOfElement implements ExpectedCondition<Boolean> {

    private final WebElement element;

    /**
     * Constructor method VisibilityOfElement.
     * @param element : WebElement parameter
     */
    public VisibilityOfElement(WebElement element) {
        this.element = element;
    }

    @Override
    public Boolean apply(WebDriver d) {
        try {
            return element.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException | ElementNotVisibleException e) {
            return false;
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
}
