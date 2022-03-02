package ui.pagemodel.pages.utils;

import org.openqa.selenium.By;

/**
 * SelectElementByType class.
 * @author jsiddiqui
 */
public class SelectElementByType {

    /**
     * Method to select element 'by' type.
     *
     * @param type        : String : 'By' type
     * @param locatorId : String : Locator value
     * @return By
     */
    public By getelementbytype(String type, String locatorId) {
        switch (type) {
            case "id":
                return By.id(locatorId);
            case "name":
                return By.name(locatorId);
            case "class":
                return By.className(locatorId);
            case "xpath":
                return By.xpath(locatorId);
            case "css":
                return By.cssSelector(locatorId);
            case "linkText":
                return By.linkText(locatorId);
            case "partialLinkText":
                return By.partialLinkText(locatorId);
            case "tagName":
                return By.tagName(locatorId);
            default:
                return null;

        }
        /*
         * if(type.equals("id")) return By.id(locatorId); else if
         * (type.equals("name")) return By.name(locatorId); else if
         * (type.equals("class")) return By.className(locatorId); else if
         * (type.equals("xpath")) return By.xpath(locatorId); else if
         * (type.equals("css")) return By.cssSelector(locatorId); else
         * if(type.equals("linkText")) return By.linkText(locatorId); else
         * if(type.equals("partialLinkText")) return
         * By.partialLinkText(locatorId); else if(type.equals("tagName"))
         * return By.tagName(locatorId); else return null;
         */
    }
}
