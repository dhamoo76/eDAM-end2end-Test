package ui.pagemodel.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ui.pagemodel.constants.Constants;
import ui.pagemodel.pages.AbstractPage;
import ui.pagemodel.pages.utils.ConfigManager;

/**
 * DAM Asset Steps.
 */
public class DAMAssetSteps extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(DAMAssetSteps.class);

    /**
     * This is user in homepage method.
     *
     */
    @Given("User in HomePage")
    public void userInHomePage() {
        String breadcrumb = getAssetObj().getBreadcrumbTitle();
        Assert.assertEquals(breadcrumb, Constants.BREADCRUMB_LABEL_ASSETS);
    }

    /**
     * User click on selected brand folder.
     */
    @Given("User click on selected brand folder")
    public void userClickOnSelectedBrandFolder() {
        Assert.assertTrue(getAssetObj().clickOnSelectedBrand());
    }

    /**
     * User hover over asset and perform any action.
     * @param actionType : String actionType e.g. Select Asset
     */
    @Then("^User \"(.+)\" brand folder$")
    public void userPerformActionOnBrandFolder(String actionType) {
       boolean checkStatus = getAssetObj().hoverOverElementAndPerformAction(getBrandNameByAbbreviation(ConfigManager.getBrand()), actionType);
        Assert.assertTrue(checkStatus);
    }

    /**
     * User select brand folder.
     */
    @Then("^User click on export meta data option$")
    public void userPerformAnyAction() {
        boolean checkStatus = getAssetObj().userClickOnExportMetaDataOption();
        Assert.assertTrue(checkStatus);
    }

    /**
     * User click on any option in asset/folder action bar.
     * @param option : String : option create,properties...
     */
    @And("^User click on \"(.+)\" option in action bar$")
    public void userClickOnAnyOptionInActionBar(String option) {
        boolean checkStatus = getAssetObj().userClickOnAnyOptionInActionBar(option);
        Assert.assertTrue(checkStatus);
    }

    /**
     * User perform any action in selective form.
     * @param option : String option e.g
     * @param form : String form name
     */
    @And("^User click on \"(.+)\" option in \"(.+)\" form$")
    public void userClickOnAnyOption(String option, String form) {
        boolean checkStatus = getAssetObj().performActionInForm(option, form);
        Assert.assertTrue(checkStatus);
    }

    /**
     * User enter data in export metadata detail form.
     * @param table : Datatable from feature file
     */
    @Then("User enter data in export metadata details form")
    public void userEnterDataInExportMetadataDetailsForm(DataTable table) {
        boolean checkStatus = getAssetObj().fillExportMetadata(table);
        Assert.assertTrue(checkStatus);
    }

}
