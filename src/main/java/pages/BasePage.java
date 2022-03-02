package pages;

import factory.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.Robot;
import java.awt.AWTException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is Base Class for all individual Pages class
 * This class holding driver,common webElements and explicit/Fluent wait and JavaScriptExecutor objects
 */
public class BasePage {
	protected Common common;
	public WebDriver driver;
	public JavascriptExecutor js;
	public Robot robot;
	public WebDriverWait jsWait;
	public Actions act;
	public FluentWait<WebDriver> fluentWait;
    private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());

	public BasePage(WebDriver driver) {
		try {
			this.driver = driver;
			jsWait = new WebDriverWait(this.driver, Common.ExplicitTime);
			fluentWait = new FluentWait(this.driver);
			js = (JavascriptExecutor) driver;
			act = new Actions(driver);
			robot = new Robot();
		} catch (AWTException e) {
			LOGGER.log(Level.ALL, "Error while Initiation");
		}
	}

	/**
	 * This is generic wait will be used wherever needed,
	 * this will use to wait until the time configured in common.properties
	 */
	protected void asyncWait() {
		try {
			Thread.sleep(Common.AsyncWaitTime);
		} catch (Exception e) {
        	LOGGER.log(Level.ALL, "Error while waiting for Page/Element(s) :", e.getMessage());
		}
	}

	/**This will use to verify the return the specific file to be uploaded from the specified directory
	 * @param dirPath Directory path from where file to be read
	 * @param fileName File name which has to pick for uploading process
	 * @return full path of the file which to be uplodaed
	 */
	protected String getImportFile(String dirPath, String fileName) {
		String fName = null;
		try {
			File dir = new File(dirPath);
			File[] files = dir.listFiles();
			if (files == null || files.length == 0)
				return fName;

			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(fileName))
					fName = dirPath + "\\" + files[i].getName();
			}
		} catch (Exception e) {
        	LOGGER.log(Level.ALL, "Error while reading downloaded file :" , e.getMessage());
		}
		return fName;
	}

	/** This is JavaScript method to use to perform double-click any web Elements
	 *
	 * @param element Pass webElement to be performed DoubleClick
	 */
	protected void DoubleClick(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
					+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
					+ "arguments[0].dispatchEvent(evt);", element);
		} catch (Exception e) {
        	LOGGER.log(Level.ALL, "Error while double clicking the element :" + element.getText() + "Message ", e.getMessage());
		}
	}

	/**This will use to return page Title
	 * @return Page Title
	 */
	protected String getPageTitle() {
		return driver.getTitle();
	}


	/** This will used to select the particular brand in the home page
	 * @param brand Pass the brand which need to select from home page
	 */
	public void SelectBrand(String brand) {
		try {
			By brandXpath;
			switch (brand.toLowerCase()) {
			case "pb":
				brandXpath = By.xpath("//*[@id='pottery-barn-anchor']");
				break;
			case "mg":
				brandXpath = By.xpath("//*[@id='mark-and-graham-anchor']");
				break;
			case "rejuvenation":
				brandXpath = By.xpath("//*[@id='rejuvenation-anchor']");
				break;
			case "ws":
				brandXpath = By.xpath("//*[@id='williams-sonoma-anchor']");
				break;
			default:
				brandXpath = By.xpath("//*[@id='west-elm-anchor']");
				break;
			}
			driver.findElement(brandXpath).click();
		} catch (Exception e) {
        	LOGGER.log(Level.ALL, "Error while selecting Brand in eDAM" , e.getMessage());
		}
	}


	/** This common method used to change view of tha page
	 * @param type Pass the view Type either Content view or List View
	 */
	public void ChangeContentView(String type) {
		
		try {
			WebElement leftNavigationBtn = driver.findElement(By.id("coral-id-7"));
			leftNavigationBtn.click();
			By brandXpath;
			switch (type.toLowerCase()) {
			case "contentonly":
				brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Content Only')]");
				break;
			case "treeview":
				brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Content Tree')]");
				break;
			case "navigation":
				brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Navigation')]");
				break;
			case "timeline":
				brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Timeline')]");
				break;
			case "filter":
				brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Filter')]");
				break;
			default:
				brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Content Only')]");
				break;
			}
			driver.findElement(brandXpath).click();
		} catch (Exception e) {
        	LOGGER.log(Level.ALL, "Error while selecting content view" , e.getMessage());
		}
	}

	/**
	 * @param element pass the WebElement to be clicked
	 * @return Boolean , return true if clicked, otherwise return false
	 */
	protected boolean ElementClick(WebElement element) {
		try {
			element.click();
			return  true;

		}catch (Exception e)
		{
			return false;
		}
	}
}
