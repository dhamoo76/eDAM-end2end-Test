package ui.pagemodel.pages.utils;

/**
 * Application ConfigManager class.
 * @author jsiddiqui
 */
public final class ConfigManager {

    /**
     * Config Manager Constructor.
     */
    private ConfigManager() {

    }

    //Command-line properties
    private static String brand;
    private static String environment; // dev, qa, uat
    private static String coast; // east or west
    private static String browser; //chrome, firefox,
    private static String featurePath;
    private static String wsiEdamAEMUrl;

    /**
     * Get Brand method.
     * @return : Return String Brand
     */
    public static String getBrand() {
        return brand;
    }

    /**
     * Set Brand method.
     * @param brand : String : brand
     */
    public static void setBrand(String brand) {
        ConfigManager.brand = brand;
    }

    /**
     * Get Environment method.
     * @return Return String: environment e.g. qa, dev
     */
    public static String getEnvironment() {
        return environment;
    }

    /**
     * Set Environment Method.
     * @param environment : String environment parameter
     */
    public static void setEnvironment(String environment) {
        ConfigManager.environment = environment;
    }

    /**
     * Get Coast Method.
     * @return Return String: coast e.g. east / west
     */
    public static String getCoast() {
        return coast;
    }

    /**
     * Set coast method.
     * @param coast : String: coast parameter
     */
    public static void setCoast(String coast) {
        ConfigManager.coast = coast;
    }

    /**
     * Get Browser method.
     * @return Return String: browser
     */
    public static String getBrowser() {
        return browser;
    }

    /**
     * Set Browser method.
     * @param browser String: browser
     */
    public static void setBrowser(String browser) {
        ConfigManager.browser = browser;
    }

    /**
     * Set Feature Path method e.g. src/../file.feature.
     * @return Return : String feature path
     */
    public static String getFeaturePath() {
        return featurePath;
    }

    /**
     * Set Feature path method.
     * @param featurePath String : featurePath parameter
     */
    public static void setFeaturePath(String featurePath) {
        ConfigManager.featurePath = featurePath;
    }

    /**
     * Get WSI Edam AEM URL.
     * @return Return String : wsiEdamAEMUrl
     */
    public static String getWsiEdamAEMUrl() {
        return wsiEdamAEMUrl;
    }

    /**
     * Set WSI Edam AEM Url.
     * @param wsiEdamAEMUrl String: wsiEdamAEMUrl
     */
    public static void setWsiEdamAEMUrl(String wsiEdamAEMUrl) {
        ConfigManager.wsiEdamAEMUrl = wsiEdamAEMUrl;
    }
}
