package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions( tags = {"@blog_page"},glue = {"stepdefs"}, plugin = {"html:target/cucumber-reports/blogpage/cucumber-pretty","json:target/json-cucumber-reports/blogpage/cukejson.json",
		"testng:target/testng-cucumber-reports/blogpage/cuketestng.xml" }, features = {"src/test/resources/features/BlogPage"})
public class BlogPageRunner extends AbstractTestNGCucumberParallelTests {
	
	@BeforeClass
	public static void before() {


	}

	@AfterClass
	public static void after() {

	}

}
