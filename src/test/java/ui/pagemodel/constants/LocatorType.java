package ui.pagemodel.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum find element by locator Type.
 * @author jsiddiqui 
 */
@Getter
@AllArgsConstructor
public enum LocatorType {
    /**
     * LocatorType ID.
     */
    ID("id"),
    /**
     * LocatorType name.
     */
    NAME("name"),
    /**
     * LocatorType class.
     */
    CLASS("class"),
    /**
     * LocatorType xpath.
     */
    XPATH("xpath"),
    /**
     * LocatorType css.
     */
    CSS("css"),
    /**
     * LocatorType linkText.
     */
    LINK_TEXT("linkText"),
    /**
     * LocatorType partialLinkText.
     */
    PARTIAL_LINK_TEXT("partialLinkText"),
    /**
     * LocatorType tagName.
     */
    TAG_NAME("tagName");

    private String name;
}
