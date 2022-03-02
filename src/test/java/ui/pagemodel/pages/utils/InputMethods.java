package ui.pagemodel.pages.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ui.pagemodel.constants.Constants;
import ui.pagemodel.pages.AbstractPage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Input method class.
 * Find element and enter and clear text
 *
 * @author jsiddiqui
 */
public class InputMethods extends AbstractPage {
    private final SelectElementByType selectElementByType = new SelectElementByType();
    private WebElement dropdown;
    private Select selectList;

    /**
     * Method to enter text into text field.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param text       : String : Text value to enter in field
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void enterText(String locatorType, String text, String locatorId, String... args) {
        getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        getDriver().findElement(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))).sendKeys(text);
    }

    /**
     * Method to clear text of text field.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void clearText(String locatorType, String locatorId, String... args) {
        getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        getDriver().findElement(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))).clear();
    }

    /**
     * Method to select element from Dropdown by type.
     * @param select : Select : Select variable
     * @param bytype      : String : Name of by type
     * @param option      : String : Option to select
     */
    public void selectelementfromdropdownbytype(Select select, String bytype, String option) {
        switch (bytype) {
            case "selectByIndex":
                int index = Integer.parseInt(option);
                select.selectByIndex(index - 1);
                break;
            case "value":
                select.selectByValue(option);
                break;
            default: //"text":
                select.selectByVisibleText(option);
                break;
        }
    }

    /**
     * Method to select option from dropdown list.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param optionBy : String : optionBy
     * @param option     : String : Option to select
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void selectOptionFromDropdown(String locatorType, String optionBy, String option, String locatorId, String... args) {
        dropdown = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        selectList = new Select(dropdown);

        switch (optionBy) {
            case "selectByIndex":
                selectList.selectByIndex(Integer.parseInt(option) - 1);
                break;
            case "value":
                selectList.selectByValue(option);
                break;
            default:// "text":
                selectList.selectByVisibleText(option);
                break;
        }
    }

    // method to select all option from dropdwon list

    /**
     * Method to unselect all option from dropdwon list.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void unselectAllOptionFromMultiselectDropdown(String locatorType, String locatorId, String... args) {
        dropdown = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        selectList = new Select(dropdown);
        selectList.deselectAll();
    }

    /**
     * Method to unselect option from dropdwon list.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param optionBy : String : optionBy
     * @param option : String option
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void deselectOptionFromDropdown(String locatorType, String optionBy, String option, String locatorId, String... args) {
        dropdown = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType,
                getLocator(locatorId, args))));
        selectList = new Select(dropdown);

        switch (optionBy) {
            case "selectByIndex":
                selectList.deselectByIndex(Integer.parseInt(option) - 1);
                break;
            case "value":
                selectList.deselectByValue(option);
                break;
            default:// "text":
                selectList.deselectByVisibleText(option);
                break;
        }
    }

    /**
     * Method to check check-box.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void checkCheckbox(String locatorType, String locatorId, String... args) {
        WebElement checkbox = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * Method to uncheck check-box.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void uncheckCheckbox(String locatorType, String locatorId, String... args) {
        WebElement checkbox = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * Method to toggle check-box status.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void toggleCheckbox(String locatorType, String locatorId, String... args) {
        getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args)))).click();
    }

    /**
     * Method to select radio button.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void selectRadioButton(String locatorType, String locatorId, String... args) {
        WebElement radioButton = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    /**
     * Method to select option from radio button group.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param option     : String : Option to select
     * @param by         : String : Name of by type
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void selectOptionFromRadioButtonGroup(String locatorType, String option, String by, String locatorId, String... args) {
        List<WebElement> radioButtonGroup = getDriver().findElements(
                selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args)));
        for (WebElement rb : radioButtonGroup) {
            if (by.equals("value")) {
                if (rb.getAttribute("value").equals(option) && !rb.isSelected()) {
                    rb.click();
                }
            } else if (by.equals("text")) {
                if (rb.getText().equals(option) && !rb.isSelected()) {
                    rb.click();
                }
            }
        }
    }

    /**
     * Upload file using robot.
     * @param fileLocation String: fileLocation path
     */
    public void uploadFile(String fileLocation) {
        //native key strokes for CTRL, V and ENTER keys
        Robot robot;
        try {
            setClipboardData(fileLocation);
            robot = new Robot();
            String os = getOperatingSystem();
            if (os.contains("mac")) {

                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_META);
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.delay(500);
                //Open Goto window
                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_G);

                robot.keyRelease(KeyEvent.VK_G);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_META);

                robot.delay(Constants.ROBOT_POLLING_LONG);
                //Paste the clipboard value

                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_META);

                robot.delay(Constants.ROBOT_POLLING_LONG);

                //Press Enter key to close the Goto window and Upload window

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

            } else {
                // other than mac
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }
            robot.delay(Constants.ROBOT_POLLING_LONG);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(Constants.ROBOT_POLLING_LONG);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Command e.g. backspace
     */
    public void deleteFromCommand() {
        //native key strokes for CTRL, V and ENTER keys
        Robot robot;
        try {
            robot = new Robot();
            robot.delay(Constants.ROBOT_POLLING_LONG);
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set Clipboard Data method.
     * @param fileLocation String: file location e.g. src/test/resources/file.jpg
     */
    public static void setClipboardData(String fileLocation) {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(fileLocation);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    /**
     * Get Operation System.
     * @return : String OS
     */
    private String getOperatingSystem() {
        return System.getProperty("os.name").toLowerCase();
    }
}
