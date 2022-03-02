package pages;

import factory.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webConnector.Browser;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is Login Page class which is designed as per Page object Model Design pattern
 */
public class LoginPage extends BasePage {
    private static final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());

    @FindBy(id = "username")
    private WebElement loginText;
    @FindBy(id = "password")
    private WebElement passwordText;
    @FindBy(id = "submit-button")
    private WebElement signInBtn;
    @FindBy(xpath = "//coral-shell-homeanchor-label[contains(text(),'WSI Enterprise DAM')]")
    private WebElement homePageText;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(Browser.getDriver(), this);
    }

    public WebElement getLoginText() {
        return loginText;
    }

    public WebElement getPasswordText() {
        return passwordText;
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

    public WebElement getHomeText() {
        return homePageText;
    }

    public boolean EnterUserNamePassword(String uName, String password) {
        try {
            getLoginText().sendKeys(uName != null ? uName : Common.UserName);
            getPasswordText().sendKeys(password != null ? password : Common.Password);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.ALL, "Error while entering username and password - ", e.getMessage());
            return false;
        }
    }

    /**
     * This used for USer login and select the brand in  BeforeClass annotation
     */
    public void EnterCredentialAndSelectBrand() {
        try {

            EnterUserNamePassword(Common.UserName, Common.Password);
            SignInClick();
            asyncWait();
            SelectBrand(Common.Brand);
        } catch (Exception e) {
            LOGGER.log(Level.ALL, "Error While Login application");
        }
    }

    public boolean SignInClick() {
        return super.ElementClick(getSignInBtn());
    }

    public boolean verifyHomePage() {

        try {
            WebElement homeTxt = driver
                    .findElement(By.xpath("//coral-shell-homeanchor-label[contains(text(),'WSI Enterprise DAM')]"));
            if (homeTxt.getText() != null)
                return true;
            else
                return false;

        } catch (Exception e) {
            LOGGER.log(Level.ALL, "Error while verify Home page - ", e.getMessage());
            return false;
        }
    }

}
