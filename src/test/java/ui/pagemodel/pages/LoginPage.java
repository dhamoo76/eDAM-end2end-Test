package ui.pagemodel.pages;

import ui.pagemodel.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.constants.LocatorType;

/**
 * Login page class.
 * @author jsiddiqui
 */
public class LoginPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    /**
     * Enter username and password.
     */
    public void enterUserNamePassword() {
        try {
            getInputObj().enterText(LocatorType.ID.getName(), System.getProperty(Constants.TEST_USER_PROP_KEY), "login.username.id");
            getInputObj().enterText(LocatorType.ID.getName(), System.getProperty(Constants.TEST_PASS_PROP_KEY), "login.password.id");
        } catch (Exception e) {
            LOGGER.error("Error while entering username and password: {}", e.getMessage());
        }
    }


    /**
     * User enter username and password to login.
     */
    public void login() {
        try {
            enterUserNamePassword();
            getClickObj().click(LocatorType.ID.getName(), "login.button");
        } catch (Exception e) {
            LOGGER.error("Error While Login application: {}", e.getMessage());
        }
    }

    /**
     * This is SignInClick method.
     * @return boolean true/false
     */
    public boolean signInClick() {
        getClickObj().click(LocatorType.ID.getName(), "login.button");
        return  true;
    }

}
