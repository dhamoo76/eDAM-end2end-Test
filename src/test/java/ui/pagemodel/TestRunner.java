package ui.pagemodel;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import ui.pagemodel.constants.Constants;
import io.cucumber.junit.CucumberOptions;

/**
 * TestRunner default class.
 * @author jsiddiqui
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {Constants.GLUE_STEPS}, plugin = {
        Constants.HTML_TARGET_CUCUMBER_REPORTS,
        Constants.JSON_TARGET_CUCUMBER_REPORTS_DEFAULT,
        Constants.XML_TARGET_TESTNG_CUCUMBER_REPORTS},
        features = {"src/test/resources/features/regression"})
public class TestRunner {
}

