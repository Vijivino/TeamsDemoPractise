package teamB;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersXml {

	WebDriver driver;

	@BeforeMethod
	public void launchbrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();

	}

	@Test
	@Parameters("inputdata")
	public void search(String inputdata) {

		driver.findElement(By.name("q")).sendKeys(inputdata,Keys.ENTER);
		String title = driver.getTitle();
		Assert.assertNotEquals(title, "Google");
		System.out.println("Search is done for "+inputdata);

	}

	@Test(priority=1)
	@Parameters("places")
	public void searchPlace(String places) {

		driver.findElement(By.name("q")).sendKeys(places,Keys.ENTER);
		String title = driver.getTitle();
		Assert.assertNotEquals(title, "Google");
		System.out.println("Search is done for "+places);

	}

	@Test(priority=2,enabled=false)
	@Parameters({"optionalvalue"}) //we are not passing the param as name="optionvalue" in xml
	public void searchOptional(@Optional String optionalvalue) {

		//driver.findElement(By.name("q")).sendKeys(optionalvalue,Keys.ENTER);
		String title = driver.getTitle();
		//Assert.assertNotEquals(title, "Google");
		System.out.println("Since no value is passed as parameter in xml file, it should consider default value null as parameter in "+title);

	}



	@AfterMethod
	public void close() {
		driver.quit();
	}

}
