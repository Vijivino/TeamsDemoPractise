package teamCrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features/DataTable.feature", glue = {"teamCstepdefinitions"}, 
                     plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
                     monochrome=true)


public class DatatableRunner {

}
