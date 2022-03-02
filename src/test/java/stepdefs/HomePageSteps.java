package stepdefs;

import pages.BasePage;
import pages.Homepage;
import webConnector.Browser;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {
	Homepage homePage;
	WebDriver driver ;
	Common comm;
	public HomePageSteps() throws AWTException {
		driver = Browser.getDriver();
		homePage = new Homepage(driver);
		comm= new Common(driver);
	}

	@Given("User in HomePage")
	public void user_in_HomePage() {
		// Write code here that turns the phrase above into concrete actions
		//throw new cucumber.api.PendingException();
	}

	@And("User Clicks {string} Icon")
	public void user_Clicks_Icon(String brandName) {
		//Browser.SelectBrand(brandName);
	}

	@Then("User navigate to Corresponding brand page")
	public void user_navigate_to_Corresponding_brand_page() {
		Common.waitForPagetoLoad();
		String returnString = homePage.VerifyBrandLink();
		Assert.assertEquals(Common.Brand.toLowerCase(), returnString.toLowerCase());
	}
}
