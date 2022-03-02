package ui.pagemodel.pages.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.constants.Constants;

/**
 * This class is used to initialize the web driver, based on the browser type
 * in the application.properties file.
 * @author jsiddiqui
 */
public class DriverManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.quit();
        }
    };

    private static WebDriver driver;
    private PropertiesReader applicationProperties;

    /**
     * By default to web driver will be firefox.
     * Override it by passing -Dbrowser=Chrome to the command line arguments
     *
     * @return webdriver
     */
    private WebDriver chooseDriver() {
        String preferredDriver = System.getProperty("browser", applicationProperties.get("browser"));
        boolean headless = System.getProperty("headless", "false").equals("true");
        LOGGER.info("Setting up driver for {}", preferredDriver);

        switch (preferredDriver.toLowerCase()) {
            case "safari":
                return new SafariDriver();
            case "edge":
                return new EdgeDriver();
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                if (headless) {
                    chromeOptions.addArguments("headless");
                }
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("window-size=1920,1080");
                chromeOptions.addArguments("-incognito");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                return new ChromeDriver(chromeOptions);
            default:
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.setHeadless(true);
                }
                return new FirefoxDriver(options);
        }
    }


    /**
     * Setup Drivers and environments.
     * @return Return drivers
     */
    public WebDriver getDefaultDriver() {

        getEnvironmentDetails();


        if (DRIVER_THREAD_LOCAL.get() != null) {
            return DRIVER_THREAD_LOCAL.get();
        }
        driver = chooseDriver();
        driver.get(ConfigManager.getWsiEdamAEMUrl());
        driver.manage().window().maximize();
        DRIVER_THREAD_LOCAL.set(driver);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        return getDriver();
    }

    /**
     * This is getter method.
     * @return driver WebDriver Object to load.
     *
     */
    public WebDriver getDriver() {
        if (DRIVER_THREAD_LOCAL.get() != null) {
            return DRIVER_THREAD_LOCAL.get();
        } else {
            return getDefaultDriver();
        }
    }

    /**
     * Read application config.
     */
    private void getEnvironmentDetails() {
        try {
            applicationProperties = new PropertiesReader("application.properties");

            final String brand = System.getProperty("brand", applicationProperties.get("brand")).toLowerCase();

            //Environment properties e.g. QA, DEV, UAT
            final String environment = System.getProperty("environment", applicationProperties.get("environment")).toLowerCase();

            //EDAM Instances e.g. east or west
            final String coast = System.getProperty("coast", applicationProperties.get("coast")).toLowerCase();

            //Browser property to choose web drivers
            final String browser = System.getProperty("browser", applicationProperties.get("browser")).toLowerCase();

            ConfigManager.setBrand(brand);
            ConfigManager.setEnvironment(environment);
            ConfigManager.setCoast(coast);
            ConfigManager.setBrowser(browser);

            getWSIEdamAEMUrl(environment, coast);

        } catch (Exception e) {
            LOGGER.error("Error while configuring common data: {}", e.getMessage());
        }
    }

    /**
     * Get WSI EDAM AEM Url from properties file.
     * @param environment : String : environment e.g. qa,dev,rgs
     * @param coast : String: coast e.g. east / west
     */
    private void getWSIEdamAEMUrl(String environment, String coast) {
        String aemUrl = applicationProperties.get(String.format(Constants.WSI_EDAM_AEM_URL, environment, coast));
        if (null != aemUrl) {
            ConfigManager.setWsiEdamAEMUrl(aemUrl);
        } else {
            ConfigManager.setWsiEdamAEMUrl(applicationProperties.get("wsi.edam.aem.url"));
        }
    }
}
