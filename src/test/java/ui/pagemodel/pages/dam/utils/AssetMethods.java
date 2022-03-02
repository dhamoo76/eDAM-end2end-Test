package ui.pagemodel.pages.dam.utils;

import io.cucumber.datatable.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.constants.LocatorType;
import ui.pagemodel.pages.AbstractPage;
import ui.pagemodel.pages.utils.ConfigManager;

import java.util.List;
import java.util.Map;

/**
 * DAM Asset class.
 */
public class AssetMethods extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssetMethods.class);

    /**
     * Export metadata.
     *
     * @param assetTitle String : provide title to find asset folder.
     * @param actionType String : action type e.g. Select
     * @return : Return : true in case of passed
     */
    public boolean hoverOverElementAndPerformAction(String assetTitle, String actionType) {
        try {
            getClickObj().waitUntilPropertiesAppearsAndClick(
                    getClickObj().getElement(LocatorType.XPATH.getName(), "dam.asset", assetTitle),
                    LocatorType.XPATH.getName(), "asset.hover.perform.action", actionType);
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while hover over DAM asset folder: {}", ex.getMessage());
            return false;
        }
    }


    /**
     * Export metadata.
     *
     * @return : Return : true in case of passed
     */
    public boolean userClickOnExportMetaDataOption() {
        try {
            getClickObj().click(LocatorType.XPATH.getName(), "asset.switcher.more.export.metadata");
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while hover over DAM asset folder: {}", ex.getMessage());
            return false;
        }
    }

    /**
     * Handles all opens in actionbar.
     * Switcher/Action bar appeared after selecting any asset/folder.
     * @param option : String : option e.g. more, create, properties
     * @return : Return : true in case of passed
     */
    public boolean userClickOnAnyOptionInActionBar(String option) {
        try {
            if (option.equalsIgnoreCase("more")) {
                getClickObj().click(LocatorType.XPATH.getName(), "asset.switcher.more");
            } else if (option.equalsIgnoreCase("export")) { //export metadata
                getClickObj().click(LocatorType.XPATH.getName(), "asset.switcher.more.export.metadata");
            }
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while clicking option in action bar: {}", ex.getMessage());
            return false;
        }
    }

    /**
     * Handles all opens in actionbar.
     * Switcher/Action bar appeared after selecting any asset/folder.
     * @param option : String : option e.g. more, create, properties
     * @param form  : String: form name
     * @return : Return : true in case of passed
     */
    public boolean performActionInForm(String option, String form) {
        try {
            if (form.equalsIgnoreCase("exportMetaData")) {
                if (option.equalsIgnoreCase("export")) {
                    getClickObj().click(LocatorType.XPATH.getName(), "asset.export.metadata.form.button", "export");
                }
            }
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while clicking option in action bar: {}", ex.getMessage());
            return false;
        }
    }


    /**
     * Export metadata.
     *
     * @param table DataTable : table
     * @return Return: true if user able to fill form
     */
    public boolean fillExportMetadata(DataTable table) {
        try {
            List<Map<String, String>> rows = table.asMaps(String.class, String.class);
            getInputObj().enterText(LocatorType.XPATH.getName(), rows.get(0).get("exportFileName"), "asset.export.metadata.form.title");
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while export meta data form fill: {}", ex.getMessage());
            return false;
        }
    }

    /**
     * Get Breadcrumb text label.
     * @return text
     */
    public String getBreadcrumbTitle() {
        return getAssertionObj().getElementText(LocatorType.XPATH.getName(), "actionbar.breadcrumb.label");
    }

    /**
     * Get Breadcrumb text label.
     * @return text
     */
    public boolean clickOnSelectedBrand() {
        getClickObj().click(LocatorType.XPATH.getName(), "dam.asset", getBrandNameByAbbreviation(ConfigManager.getBrand()));
        return true;
    }
}
