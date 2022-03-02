package stepdefs;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import webConnector.Browser;

public class LoginSteps {
	LoginPage log;

	public LoginSteps() throws Exception {
		log = new LoginPage(Browser.getDriver());
	}

	@Given("User Navigate to Login Page")
	public void user_Navigate_to_Login_Page() {

	}

	@And("Enter User Credentials")
	public void enter_User_Credentials() {
		boolean isSuccess = log.EnterUserNamePassword(Common.UserName, Common.Password);
		Assert.assertTrue(isSuccess);
	}

	@And("User Clicks SignIn Button")
	public void user_Clicks_SignIn_Button() {
		boolean isSuccess = log.SignInClick();
		Assert.assertTrue(isSuccess);
	}

	@Then("User Should See home page")
	public void user_Should_See_home_page() {
		boolean isSuccess = log.verifyHomePage();
		log.SelectBrand(Common.Brand); // Goto default brand page
		Assert.assertTrue(isSuccess);
	}

}
