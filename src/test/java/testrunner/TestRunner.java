package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // Rerun failed tests from rerun.txt file
        features = {"src/test/resources/features/"},
        glue = {"stepdefinitions", "hooks"},
        stepNotifications = true,
        plugin = {"pretty",
                "html:target/cucumber/report.html",
                "json:target/cucumber/report.json"
//                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//                "rerun:target/rerun.txt"  // Save Failed test scenarios in rerun.txt file
        },
        tags = "@test",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)

public class TestRunner {

        @BeforeClass
        public static void setup() {
                System.out.println("In Before");
        }

        @AfterClass
        public static void teardown() {
                System.out.println("In After");
        }

}
