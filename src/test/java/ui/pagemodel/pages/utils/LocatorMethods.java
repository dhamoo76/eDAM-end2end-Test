package ui.pagemodel.pages.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.pages.AbstractPage;

import java.io.IOException;

/**
 * Locator Method class read locators.properties values by key.
 * @author jsiddiqui
 */
public class LocatorMethods extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocatorMethods.class);
    private PropertiesReader locators;

    /**
     * Get locator value by Key from locators.properties.
     * @param locatorId : locator key
     * @param args : String... args replace %s %s in locator key
     * @return : Return Locator value
     */
    public String getLocator(String locatorId, String... args) {
        try {
            locators = new PropertiesReader("locators.properties");
        } catch (IOException e) {
            LOGGER.error("Error while LocatorMethods class: {}", e.getMessage());
        }
        return String.format(locators.get(locatorId), (Object[]) args);
    }

    /**
     * Find Brand Name from abbreviation.
     * @param brand abbreviation
     * @return Return brand name from abbreviation
     */
    public String getBrandNameByAbbreviation(String brand) {
            String brandName;
            switch (brand.toLowerCase()) {
                case "pb":
                    brandName = "pottery-barn";
                    break;
                case "mg":
                    brandName = "mark-and-graham";
                    break;
                case "rj":
                    brandName = "rejuvenation";
                    break;
                case "ws":
                    brandName = "williams-sonoma";
                    break;
                default:
                    brandName = "west-elm";
                    break;
            }
            return brandName;
    }

}
