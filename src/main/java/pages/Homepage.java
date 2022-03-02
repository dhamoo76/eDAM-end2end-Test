package pages;

import factory.Common;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webConnector.Browser;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


/**
 * This is the Home Page which is designed as per Page object Model
 */
public class Homepage extends BasePage {
	private static final Logger LOGGER = Logger.getLogger(Homepage.class.getName());

	Browser wc = new Browser();

	@FindBy(xpath = "//coral-shell-label[@id='west-elm-label']")
	WebElement westElmBtn;
	@FindBy(xpath = "//a[@id='rejuvenation-anchor']")
	WebElement rejuvenationBtn;
	@FindBy(xpath = "//betty-titlebar[@id='granite-shell-actionbar']//betty-titlebar-secondary//button[@type='button']//coral-button-label[contains(text(),'Create')]")
	WebElement createBtn;
	@FindBy(xpath = "//a[@id='coral-id-10']//div[contains(@class,'coral3-BasicList-item-outerContainer')]//div[contains(@class,'coral3-BasicList-item-contentContainer')]//coral-list-item-content[@class='coral3-BasicList-item-content'][contains(text(),'Files')]")
	WebElement filesBtn;
	@FindBy(xpath = "//coral-dialog[@class='coral3-Dialog uploadListDialog is-open']//div[@class='coral3-Dialog-wrapper']//coral-dialog-footer[@class='coral3-Dialog-footer']//button[@class='coral3-Button coral3-Button--primary dam-asset-upload-button']")
	WebElement uploadBtn;
	@FindBy(xpath = "//betty-breadcrumbs[@id='granite-collection-breadcrumbs-toggle']")
	WebElement brandNameLink;

	public Homepage(WebDriver dt) {
		super(dt);
		PageFactory.initElements(wc.getDriver(), this);
	}

	public void SelectBrand(String brandName) {
		try {
			if (brandName.equalsIgnoreCase("WestElm")) {
				act.click(westElmBtn).perform();
				asyncWait();
			}
			if (brandName.equalsIgnoreCase("Rejuvenation")) {
				act.click(rejuvenationBtn).perform();
				asyncWait();
			}
		} catch (Exception e) {
			LOGGER.log(Level.ALL, "Error while clicking brand name" + brandName + "", e.getMessage());
		}
	}

	public String VerifyBrandLink() {
		int currentTabIndex = Browser.GetCurrentTabIndex(); // Getting current tab index
		Browser.SwitchTab((currentTabIndex+1)); // Move to newly opened tab
		asyncWait();
		String linkText = "";
		try {

			WebElement ele = jsWait.until(elementToBeClickable(brandNameLink));
			linkText = ele.getText();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return linkText;
	}
}