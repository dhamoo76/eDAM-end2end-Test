package factory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class can have all common methods such as wait for Page Load and file upload etc.
 * those are used in other page level class
 */
public class Common {
    private static final Logger LOGGER = Logger.getLogger( Common.class.getName() );

    public static String UploadPath;
    public static String Appurl;
    public static Long ExplicitTime;
    public static Long ImplicitTime;
    public static Long AsyncWaitTime;
    public static int FileUploadTime;
    public static String UserName;
    public static String Password;
    public static String Brand;
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private Robot robot;

    
    public Common(WebDriver driver) throws AWTException {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        robot = new Robot();
    }

    /** This is method used to wait until page get loaded
     * @return Boolean
     */
    public static boolean waitForPagetoLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Common.ExplicitTime);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) js.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    LOGGER.log(Level.ALL, "Error While page wait");
                    return false;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    /**
     * @param fName File name to be uploaded
     * @return Boolean, return true if its success otherwise return false
     */
    public Boolean uploadFile(StringSelection fName) {
        try {
            int wTime =Common.FileUploadTime;
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fName, null);
            Thread.sleep(wTime);
            robot.keyPress(KeyEvent.VK_ENTER);
            // Release Enter
            robot.keyRelease(KeyEvent.VK_ENTER);
            // Press CTRL+V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            // Release CTRL+V
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            Thread.sleep(wTime);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(wTime);
            return true;
        } catch (Exception e) {
        	LOGGER.log(Level.ALL, "Error while uploading File +" + fName.toString() + "with : ", e.getMessage());            
            return false;
        }
    }

}
