package teamCstepdefinitions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GlobalHooksTest {

	WebDriver driver;

	@Before
	public void launch() {

		System.out.println("Launching the browser...");
	}


	@Given("launch the browser")
	public void launch_the_browser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		
		//disabling the ads
		ChromeOptions options=new ChromeOptions();
		options.addArguments("disable-notifications");
		driver=new ChromeDriver(options);
		driver.get("https://www.globalsqa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}
	
	@BeforeStep
	public void print(Scenario scenario) throws WebDriverException, IOException
	{
		//Scenario scenario
		System.out.println("In the correct page ");
	//	File source1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	//	File destiny1=new File("C:\\Selenium Software\\GlobalHooks\\screenshot.png");
	//	FileUtils.copyFile(source1, destiny1);
		if(scenario.isFailed()) {
	    	   TakesScreenshot ts= (TakesScreenshot) driver;
	       	byte[] scn= ts.getScreenshotAs(OutputType.BYTES);
	       	scenario.attach(scn, "image/png", scenario.getName());
		} 
		
	}
	
	@When("Click contact us")
	public void click_contact_us() {
		
		driver.findElement(By.xpath("//li[@id='menu-item-1561']/a[text()='Contact Uss']")).click();

	}
	
	@AfterStep
	public void printafter(Scenario scenario) throws IOException {
		
//		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		File destiny=new File("C:\\Selenium Software\\Ex3BuyLaptop0411\\laptopcart.png");
//		FileUtils.copyFile(source, destiny);
//	
		if(scenario.isFailed()) {
	    	   TakesScreenshot ts= (TakesScreenshot) driver;
	       	byte[] scn= ts.getScreenshotAs(OutputType.BYTES);
	       	scenario.attach(scn, "image/png", scenario.getName());
		}
		
//		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"contact us displayed")
//		.addScreenCaptureFromPath("/Screenshots/embedded1.PNG");
//			
		//System.out.println("The contact us link is displayed");
		
	}
	
	@Then("Validate the fields displayed")
	public void validate_the_fields_displayed() {

		boolean displayed = driver.findElement(By.id("comment_name")).isDisplayed();
		Assert.assertTrue(displayed);
		System.out.println("The contact us form displayed");
		
	}
	
	@After
	public void close() {

		//driver.close();
		//System.out.println("Closing the browser...");
	}


}

