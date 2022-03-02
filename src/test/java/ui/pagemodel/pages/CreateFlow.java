package ui.pagemodel.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.constants.Constants;
import ui.pagemodel.constants.LocatorType;

import java.util.List;
import java.util.Map;

/**
 * Create Button Flow Class.
 * @author jsiddiqui
 */
public class CreateFlow extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateFlow.class);

    /**
     * Click on option appears after clicking create button.
     * @param actionItem provide one of below to perform action.
     *                   Import UGC, Folder, Files, Content Fragment, Live Copy.
     * @return true in case of actionPerformed
     */
    public boolean clickCreateButtonListOption(String actionItem) {
        try {
            if (actionItem.equalsIgnoreCase(Constants.CREATE_FOLDER_OPTION)) {
                getClickObj().click(LocatorType.XPATH.getName(), "actionbar.create.folder");
            }
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while click on create options: {}", ex.getMessage());
            return false;
        }
    }

    /**
     * Click On Create Button.
     * @return true in case of success
     */
    public boolean clickOnCreateButton() {
        try {
            getClickObj().click(LocatorType.XPATH.getName(), "actionbar.create");
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while click on create button: {}", ex.getMessage());
            return false;
        }
    }

    /**
     * User enter data and click on save.
     * @param mapList defined in feature file.
     * @return true in case of success.
     */
    public boolean enterDataAndSave(List<Map<String, String>> mapList) {
        boolean isCreated = false;
        for (Map<String, String> list : mapList) {
            //Creating folder
            if (list.get("OptionsList").equalsIgnoreCase(Constants.CREATE_FOLDER_OPTION)) {
                isCreated = createFolderInEDAM(list);
            }
        }
        return isCreated;
    }

    /**
     * Filling Data and click on Save.
     * @param formDataMap taking from create step definition file.
     * @return true in case of success
     */
    private boolean createFolderInEDAM(Map<String, String> formDataMap) {
        try {
            getInputObj().enterText(LocatorType.XPATH.getName(), formDataMap.get("Title"), "actionbar.create.folder.title");
            getInputObj().enterText(LocatorType.XPATH.getName(), formDataMap.get("Name"), "actionbar.create.folder.name");
            getClickObj().click(LocatorType.XPATH.getName(), "create.folder.save.button");
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while filing create form {}", ex.getMessage());
            return false;
        }
    }


    /**
     * Delete selected folder or asset.
     * @param mapList data caller method
     * @return true
     */
    public boolean deleteSelectedFolderOrAsset(List<Map<String, String>> mapList) {
        boolean isDeleted = false;
        for (Map<String, String> list : mapList) {
            if (list.get("OptionsList").equalsIgnoreCase(Constants.CREATE_FOLDER_OPTION)) {
                isDeleted = deleteFolderOrAsset(list);
            }
        }
        return isDeleted;
    }

    /**
     * Filling Data and click on Save.
     * @param formDataMap taking from create step definition file.
     * @return true in case of success
     */
    private boolean deleteFolderOrAsset(Map<String, String> formDataMap) {
        try {
            getClickObj().waitUntilPropertiesAppearsAndClick(
                    getClickObj().getElement(LocatorType.XPATH.getName(), "asset.title", formDataMap.get("Title")),
                    LocatorType.XPATH.getName(), "asset.quickaction.select");

            getClickObj().click(LocatorType.XPATH.getName(), "asset.switcher.more");
            getClickObj().click(LocatorType.XPATH.getName(), "asset.switcher.command.backspace");
            getClickObj().click(LocatorType.XPATH.getName(), "asset.delete.confirm");
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error while delete folder : {}", ex.getMessage());
            return false;
        }

    }
}
