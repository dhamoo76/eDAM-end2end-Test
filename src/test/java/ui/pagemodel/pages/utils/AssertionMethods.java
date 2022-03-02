package ui.pagemodel.pages.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ui.pagemodel.pages.AbstractPage;

import java.util.List;

/**
 * AssertionMethod Class.
 *
 * @author jsiddiqui
 */
public class AssertionMethods extends AbstractPage {
    // This file contains assertion methods which are called from
    // predefinedStepDefinitions

    private final SelectElementByType selectElementByType = new SelectElementByType();
    private WebElement element;

    /**
     * Method to get page title.
     *
     * @return String
     */
    public String getPageTitle() {
        return getDriver().getTitle();
    }

    /**
     * Method to verify page title.
     *
     * @param title    : String : expected title
     * @param testCase : Boolean : test case [true or false]
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void checkTitle(String title, boolean testCase) throws TestCaseFailed {
        String pageTitle = getPageTitle();

        if (testCase) {
            if (!pageTitle.equals(title)) {
                throw new TestCaseFailed("Page Title Not Matched, Actual Page Title : " + pageTitle);
            }
        } else {
            if (pageTitle.equals(title)) {
                throw new TestCaseFailed("Page Title Matched, Actual Page Title : " + pageTitle);
            }
        }
    }

    /**
     * Method to verify partial page title.
     *
     * @param partialTitle : String : partial title string
     * @param testCase     : Boolean : test case [true or false]
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void checkPartialTitle(String partialTitle, boolean testCase) throws TestCaseFailed {
        String pageTitle = getPageTitle();
        if (testCase) {
            if (!pageTitle.contains(partialTitle)) {
                throw new TestCaseFailed("Partial Page Title Not Present, Actual Page Title : " + pageTitle);
            }
        } else {
            if (pageTitle.contains(partialTitle)) {
                throw new TestCaseFailed("Partial Page Title Present, Actual Page Title : " + pageTitle);
            }
        }
    }

    /**
     * Method to get element text.
     *
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId  : String :  Locator key from locators.properties
     * @param args       String: replace locator %s
     * @return String : Return element text
     */
    public String getElementText(String locatorType, String locatorId, String... args) {
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        return element.getText();

    }

    /**
     * Method to check element text.
     *
     * @param locatorType  : String : Locator type (id, name, class, xpath, css)
     * @param actualValue : String : Expected element text
     * @param testCase    : Boolean : test case [true or false]
     * @param locatorId   : String : Locator value
     * @param args        : String: replace locator value %s %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void checkElementText(String locatorType, String actualValue, boolean testCase, String locatorId, String... args)
            throws TestCaseFailed {
        String elementText = getElementText(locatorType, getLocator(locatorId, args));

        if (testCase) {
            if (!elementText.equals(actualValue)) {
                throw new TestCaseFailed("Text Not Matched");
            }
        } else {
            if (elementText.equals(actualValue)) {
                throw new TestCaseFailed("Text Matched");
            }

        }
    }

    /**
     * Method to check partial element text.
     *
     * @param locatorType  : String : Locator type (id, name, class, xpath, css)
     * @param actualValue : String : Expected element text
     * @param testCase    : Boolean : test case [true or false]
     * @param locatorId   : String : Locator value
     * @param args        : String: replace locator value %s %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void checkElementPartialText(String locatorType, String actualValue, boolean testCase, String locatorId, String... args)
            throws TestCaseFailed {
        String elementText = getElementText(locatorType, getLocator(locatorId, args));

        if (testCase) {
            if (!elementText.contains(actualValue)) {
                throw new TestCaseFailed("Text Not Matched");
            }
        } else {
            if (elementText.contains(actualValue)) {
                throw new TestCaseFailed("Text Matched");
            }
        }
    }

    /**
     * Method to return element status - enabled?.
     *
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId  : String : Locator key from locators.properties
     * @param args : String: replace locator value %s %s in locators.properties
     * @return Boolean
     */
    public boolean isElementEnabled(String locatorType, String locatorId, String... args) {
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        return element.isEnabled();
    }

    /**
     * Element enabled checking.
     *
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param testCase   : Boolean : test case [true or false]
     * @param locatorId  : String : locator key from locators.properties
     * @param args       : String: replace locator value %s %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void checkElementEnable(String locatorType, boolean testCase, String locatorId, String... args) throws TestCaseFailed {
        boolean result = isElementEnabled(locatorType, locatorId, args);
        if (testCase) {
            if (!result) {
                throw new TestCaseFailed("Element Not Enabled");
            }
        } else {
            if (result) {
                throw new TestCaseFailed("Element Enabled");
            }
        }
    }

    /**
     * method to get attribute value.
     *
     * @param locatorType    : String : Locator type (id, name, class, xpath, css)
     * @param locatorId    : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     * @param attributeName : String : attribute name
     * @return String : Return : String Attribute name
     */
    public String getElementAttribute(String locatorType, String locatorId, String attributeName, String... args) {
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        return element.getAttribute(attributeName);
    }

    /**
     * method to check attribute value.
     *
     * @param locatorType     : String : Locator type (id, name, class, xpath, css)
     * @param attributeName  : String : attribute name
     * @param attributeValue : String : attribute value
     * @param testCase       : Boolean : test case [true or false]
     * @param locatorId     : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void checkElementAttribute(String locatorType, String attributeName,
                                      String attributeValue, boolean testCase, String locatorId, String... args) throws TestCaseFailed {
        String attrVal = getElementAttribute(locatorType, locatorId, attributeName, args);
        if (testCase) {
            if (!attrVal.equals(attributeValue)) {
                throw new TestCaseFailed("Attribute Value Not Matched");
            }
        } else {
            if (attrVal.equals(attributeValue)) {
                throw new TestCaseFailed("Attribute Value Matched");
            }
        }
    }

    /**
     * method to get element status - displayed?.
     *
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     * @return Boolean : Return true in case of element displayed
     */
    public boolean isElementDisplayed(String locatorType, String locatorId, String... args) {
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        return element.isDisplayed();
    }

    /**
     * method to check element presence.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param testCase   : Boolean : test case [true or false]
     * @param locatorId : String : Locator value
     * @param args : String : replace locatorId value %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed error
     */
    public void checkElementPresence(String locatorType, boolean testCase, String locatorId, String... args) throws TestCaseFailed {
        if (testCase) {
            if (!isElementDisplayed(locatorType, locatorId, args)) {
                throw new TestCaseFailed("Element Not Present");
            }
        } else {
            try {
                if (isElementDisplayed(locatorType, locatorId, args)) {
                    throw new Exception("Present"); // since it is negative test
                }
                // and we found element
            } catch (Exception e) {
                if (e.getMessage().equals("Present")) {
                    throw new TestCaseFailed("Element Present");
                }
            }
        }
    }

    /**
     * method to assert checkbox check/uncheck.
     *
     * @param locatorType      : String : Locator type (id, name, class, xpath, css)
     * @param shouldBeChecked : Boolean : test case [true or false]
     * @param locatorId      : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed error
     */
    public void isCheckboxChecked(String locatorType, boolean shouldBeChecked, String locatorId, String... args) throws TestCaseFailed {
        WebElement checkbox = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        if ((!checkbox.isSelected()) && shouldBeChecked) {
            throw new TestCaseFailed("Checkbox is not checked");
        } else if (checkbox.isSelected() && !shouldBeChecked) {
            throw new TestCaseFailed("Checkbox is checked");
        }
    }

    /**
     * method to assert radio button selected/unselected.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param shouldBeSelected : Boolean true for selection of radio button
     * @param locatorId : String : Locator value
     * @param args String: replace locator value %s %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed error

     */
    public void isRadioButtonSelected(String locatorType, boolean shouldBeSelected, String locatorId, String... args)
            throws TestCaseFailed {
        WebElement radioButton = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        if ((!radioButton.isSelected()) && shouldBeSelected) {
            throw new TestCaseFailed("Radio Button not selected");
        } else if (radioButton.isSelected() && !shouldBeSelected) {
            throw new TestCaseFailed("Radio Button is selected");
        }
    }


    /**
     * method to assert option from radio button group is selected/unselected.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param by : String by
     * @param option : String option
     * @param shouldBeSelected : Boolean true for radio button selection
     * @param locatorId : String : Locator key from loctors.properties
     * @param args : String: replace locator value %s %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void isOptionFromRadioButtonGroupSelected(String locatorType, String by, String option, boolean shouldBeSelected, String locatorId,
                                                     String... args) throws TestCaseFailed {
        List<WebElement> radioButtonGroup = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));

        for (WebElement rb : radioButtonGroup) {
            if (by.equals("value")) {
                if (rb.getAttribute("value").equals(option)) {
                    if ((!rb.isSelected()) && shouldBeSelected) {
                        throw new TestCaseFailed("Radio Button not selected");
                    } else if (rb.isSelected() && !shouldBeSelected) {
                        throw new TestCaseFailed("Radio Button is selected");
                    }
                }
            } else if (rb.getText().equals(option)) {
                if ((!rb.isSelected()) && shouldBeSelected) {
                    throw new TestCaseFailed("Radio Button not selected");
                } else if (rb.isSelected() && !shouldBeSelected) {
                    throw new TestCaseFailed("Radio Button is selected");
                }
            }
        }
    }

    /**
     * method to get javascript pop-up alert text.
     * @return String Return: Alert Text
     */
    public String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    /**
     * method to check javascript pop-up alert text.
     *
     * @param text : String : Text to verify in Alert
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void checkAlertText(String text) throws TestCaseFailed {
        if (!getAlertText().equals(text)) {
            throw new TestCaseFailed("Text on alert pop up not matched");
        }
    }

    /**
     * Method to verify if the particular option is Selected from Dropdown.
     *
     * @param locatorType       : String : Locator type (id, name, class, xpath, css)
     * @param by               : String : Select element from dropdown by text or value
     * @param option           : String : Element to select from dropdown
     * @param shouldBeSelected : Boolean : test case [true or false]
     * @param locatorId       : String : Locator key from input properties
     * @param args : String: replace locator value %s %s in locators.properties
     * @throws TestCaseFailed : Throws TestCaseFailed when failed
     */
    public void isOptionFromDropdownSelected(String locatorType, String by, String option,
                                             boolean shouldBeSelected, String locatorId, String... args) throws TestCaseFailed {
        Select selectList;
        WebElement dropdown = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        selectList = new Select(dropdown);

        String actualValue;
        if (by.equals("text")) {
            actualValue = selectList.getFirstSelectedOption().getText();
        } else {
            actualValue = selectList.getFirstSelectedOption().getAttribute("value");
        }

        if ((!actualValue.equals(option)) && (shouldBeSelected)) {
            throw new TestCaseFailed("Option Not Selected From Dropwdown");
        } else if ((actualValue.equals(option)) && (!shouldBeSelected)) {
            throw new TestCaseFailed("Option Selected From Dropwdown");
        }
    }
}
