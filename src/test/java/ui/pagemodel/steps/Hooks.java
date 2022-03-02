package ui.pagemodel.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.pages.LoginPage;

import java.nio.charset.StandardCharsets;

/**
 * Hook class.
 */
public class Hooks {
    private final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * Before setup.
     */
    @Before
    public void beforeSetup() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }
    /**
     * Status of Scenario.
     * @param scenario Scenario Parameter
     */
    @After
    public void afterScenario(Scenario scenario) {
        endOfTest(scenario);
    }

    /**
     * Calling after each scenario for the status.
     * @param scenario Parameter Scenario
     */
    public void endOfTest(Scenario scenario) {
        if (scenario.getStatus() != null && scenario.isFailed()) {
            String filename = scenario.getName().replaceAll("\\s+", "_");
            final String featureError = scenario.getId().replaceAll("\\s+", "_").replaceAll(":", "_").split("\\.")[1];
            filename = filename + "_" + featureError;
            scenario.embed(filename.getBytes(StandardCharsets.UTF_8), "image/png", filename);
        }

        logger.info("");
        logger.info("==========================================================================");
        logger.info("================================Test " + scenario.getStatus().toString() + "===============================");
        logger.info("==========================================================================");
        logger.info("");
    }
}
