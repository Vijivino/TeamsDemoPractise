package teamCrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="@target/failedscenarios.txt",glue= {"teamCstepdefinitions"},monochrome=true)
                

public class RerunFailedRunner {

}
