package teamA;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {

	ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;
	
	
	@BeforeTest
	public void beforetest() {
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
		report=new ExtentReports();
		report.attachReporter(htmlReporter);
		test = report.createTest("Report for google image and gmail open tests");
		System.out.println("Report started @beforeTest");
		
	}
	
	@BeforeClass
	public void beforeclass() {
		System.out.println("Two tests are prepared in this class @beforeclass ");
	}

	@BeforeMethod
	public void launchbrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		test.log(Status.INFO, "Browser launched from @beforemethod");
		System.out.println("Browser launched @beforemethod");

	}

	@Test(priority=0)	
	public void checkimage() {
		boolean displayed = driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed();
		Assert.assertTrue(displayed);
		//System.out.println("image verified");
		test.log(Status.INFO, "Image of the page verified from @test1");
		System.out.println("Test 1 completed @test1");
	}

	@Test(priority=1)
	public void openGmail() {


		driver.findElement(By.xpath("//a[text()='Gmail']")).click();
		String gmailtitle = driver.getTitle();
		if(gmailtitle.contains("Gmail")){ 
	
			test.log(Status.INFO,"Gmail is opened successfully from @test2");
			System.out.println("Test 2 completed @test2");

		}
		else
		{
			test.log(Status.INFO, "Gmail not clicked");
		}
	}


	@AfterMethod
	public void afterMethod() {

		driver.quit();
		test.log(Status.INFO, "Browser closed @aftermethod");
		System.out.println("Browser closed @aftermethod");
	}


	
	@AfterTest
	public void afterTest() {
		
		test.log(Status.INFO, "Report done and closed the test @afterTest");
		report.flush();
		
		System.out.println("Report ended @afterTest");
	}
	
	@AfterClass
	public void afterclass() {
		System.out.println("Two tests are done @afterclass");
	}

}
