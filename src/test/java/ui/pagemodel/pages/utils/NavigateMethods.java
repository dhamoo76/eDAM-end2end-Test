package ui.pagemodel.pages.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pagemodel.pages.AbstractPage;

/**
 * NavigateMethods class to support browser navigation.
 * @author jsiddiqui
 */
public class NavigateMethods extends AbstractPage {
    private final SelectElementByType selectElementByType = new SelectElementByType();
    private WebElement element;
    private String oldWin;
    private String lastWinHandle;

    /**
     * Method to open link.
     *
     * @param url : String : URL for navigation
     */
    public void navigateTo(String url) {
        getDriver().get(url);
    }

    /**
     * Method to navigate back & forward.
     *
     * @param direction : String : Navigate to forward or backward
     */
    public void navigate(String direction) {
        if (direction.equals("back")) {
            getDriver().navigate().back();
        } else {
            getDriver().navigate().forward();
        }
    }

    /**
     * Method to return key by OS wise.
     *
     * @return Keys : Return control or command key as per OS
     */
    public Keys getKey() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return Keys.CONTROL;
        } else if (os.contains("nux") || os.contains("nix")) {
            return Keys.CONTROL;
        } else if (os.contains("mac")) {
            return Keys.COMMAND;
        } else {
            return null;
        }
    }

    /**
     * Method to zoom in/out page.
     *
     * @param inOut : String : Zoom in or out
     */
    public void zoomInOut(String inOut) {
        WebElement webElement = getDriver().findElement(selectElementByType.getelementbytype("tagName", "html"));
        switch (inOut) {
            case "ADD":
                webElement.sendKeys(Keys.chord(getKey(), Keys.ADD));
                break;
            case "SUBTRACT":
                webElement.sendKeys(Keys.chord(getKey(), Keys.SUBTRACT));
                break;
            default:// "reset":
                webElement.sendKeys(Keys.chord(getKey(), Keys.NUMPAD0));
                break;
        }
    }

    /**
     * Method to zoom in/out web page until web element displays.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param inOut      : String : Zoom in or out
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void zoomInOutTillElementDisplay(String locatorType, String inOut, String locatorId, String... args) {
        Actions action = new Actions(getDriver());
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        while (true) {
            if (element.isDisplayed()) {
                break;
            } else {
                action.keyDown(getKey()).sendKeys(inOut).keyUp(getKey()).perform();
            }
        }
    }

    /**
     * Method to resize browser.
     *
     * @param width  : int : Width for browser resize
     * @param height : int : Height for browser resize
     */
    public void resizeBrowser(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    /**
     * Method to maximize browser.
     */
    public void maximizeBrowser() {
        getDriver().manage().window().maximize();
    }

    /**
     * Method to hover on element.
     *  @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value from locators.properties
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void hoverOverElement(String locatorType, String locatorId, String... args) {
        Actions action = new Actions(getDriver());
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(
                        selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        action.moveToElement(element).perform();
    }

    /**
     * Method to scroll page to particular element.
     * @param locatorType : String : Locator type (id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void scrollToElement(String locatorType, String locatorId, String... args) {
        element = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Method to scroll page to top or end.
     *
     * @param to : String : Scroll page to Top or End
     * @throws Exception : Throws Exceptin in case of error
     */
    public void scrollPage(String to) throws Exception {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        if (to.equals("end")) {
            executor.executeScript(
                    "window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
        } else if (to.equals("top")) {
            executor.executeScript(
                    "window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0);");
        } else {
            throw new Exception("Exception : Invalid Direction (only scroll \"top\" or \"end\")");
        }
    }

    /**
     * Method to switch to new window.
     */
    public void switchToNewWindow() {
        oldWin = getDriver().getWindowHandle();
        for (String winHandle : getDriver().getWindowHandles()) {
            lastWinHandle = winHandle;
        }
        getDriver().switchTo().window(lastWinHandle);
    }

    /**
     * Method to switch to old window.
     */
    public void switchToOldWindow() {
        getDriver().switchTo().window(oldWin);
    }

    /**
     * Method to switch to window by title.
     *
     * @param windowTitle : String : Name of window title to switch
     * @throws Exception : Throws Exceptin in case of error
     */
    public void switchToWindowByTitle(String windowTitle) throws Exception {
        // System.out.println("++"+windowTitle+"++");
        oldWin = getDriver().getWindowHandle();
        boolean winFound = false;
        for (String winHandle : getDriver().getWindowHandles()) {
            String str = getDriver().switchTo().window(winHandle).getTitle();
            // System.out.println("**"+str+"**");
            if (str.equals(windowTitle)) {
                winFound = true;
                break;
            }
        }
        if (!winFound) {
            throw new Exception("Window having title " + windowTitle + " not found");
        }
    }

    /**
     * Method to close new window.
     */
    public void closeNewWindow() {
        getDriver().close();
    }

    /**
     * Method to switch frame using web element frame.
     *  @param locatorType : String : Locator type (index, id, name, class, xpath, css)
     * @param locatorId : String : Locator value
     * @param args : String: replace locator value %s %s in locators.properties
     */
    public void switchFrame(String locatorType, String locatorId, String... args) {
        if (locatorType.equalsIgnoreCase("index")) {
            getDriver().switchTo().frame(getLocator(locatorId, args));
        } else {
            element = getDriverWait().waitShort()
                    .until(ExpectedConditions.presenceOfElementLocated(
                            selectElementByType.getelementbytype(locatorType, getLocator(locatorId, args))));
            getDriver().switchTo().frame(element);
        }
    }

    /**
     * method to switch to default content.
     */
    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }
}
