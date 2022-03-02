package ui.pagemodel.pages.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties reader class.
 * @author jsiddiqui
 */
public class PropertiesReader {
    private final Properties properties;

    /**
     * Reading Properties from files.
     * @param propertyFileName provide file name
     * @throws IOException in case of error
     */
    public PropertiesReader(String propertyFileName) throws IOException {
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream(propertyFileName);
        this.properties = new Properties();
        this.properties.load(is);
    }

    /**
     * Get property value by name.
     * @param propertyName provide property
     * @return property value
     */
    public String get(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
}
