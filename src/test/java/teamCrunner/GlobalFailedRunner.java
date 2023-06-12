package teamCrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features/RerunGlobal.feature",glue= {"teamCstepdefinitions"},monochrome=true,
            		      // plugin= {"rerun:target/failedscenarios.txt"},
		    		   plugin={"pretty",
		    				   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)


public class GlobalFailedRunner {

}
