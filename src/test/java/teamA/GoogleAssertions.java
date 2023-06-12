package teamA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleAssertions {

	WebDriver driver;

	@BeforeMethod
	public void launchbrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();

	}
	@Test(priority=1,enabled=false)
	public void checktitle() {
		String title = driver.getTitle();

		//Hard assert
		//Assert.assertEquals(title, "Googles");
		//System.out.println("Title verified");

		//for soft assertion, should create obj
		SoftAssert SoftAssert=new SoftAssert();
		SoftAssert.assertEquals(title, "googles");
		System.out.println("Title verified");

		SoftAssert.assertNotNull(title);
		System.out.println("Title is not null  - " +title);

		//should call assertall to report the error only one time at last
		SoftAssert.assertAll();//without this asserall it always say the assertion passed eventhough it fails

	}

	@Test(priority=2,enabled=false)
	public void notequals() {
		String title = driver.getTitle();

		Assert.assertNotEquals(title, "google");
		System.out.println("Title Not equals verified");

		Assert.assertSame(title, "Google");
		System.out.println("The expected title is same as the actual title");

		//passing parameter as null and check for assertnull
		driver.findElement(By.name("q")).sendKeys("null");
		String attribute1 = driver.findElement(By.name("q")).getAttribute("value");
		Assert.assertNull(attribute1);
		System.out.println("the search box is null");

		//driver.quit();
	}

	@Test(priority=3,enabled=true)
	public void checktimage() {

		boolean displayed = driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed();
		Assert.assertTrue(displayed);
		System.out.println("Assert true image verified");

		//check empty space for null
		String attribute = driver.findElement(By.name("q")).getAttribute("value");
		SoftAssert Sassert=new SoftAssert();
		Sassert.assertNull(attribute);
		System.out.println("the search box is not null");
	

		driver.findElement(By.name("q")).sendKeys("proceeded after assertion failed");
		Sassert.assertAll();//assertall should be called at the end so that it execute the previous other step mentioned

	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

}
