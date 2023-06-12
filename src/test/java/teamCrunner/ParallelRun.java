package teamCrunner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
//to run features file in parallel,should create feature file folder name same as stepdefinition package name
@CucumberOptions(features="src/test/resources/parallel",glue={"parallel"},monochrome=true)

public class ParallelRun extends AbstractTestNGCucumberTests {
	
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
	}

}
