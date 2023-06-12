package teamB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserChrome {

	WebDriver driver;
	@BeforeMethod
	@Parameters("browser")
	public void launch(String browser) {
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get("https://demowebshop.tricentis.com/");
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{

			System.setProperty("webdriver.edge.driver","C:\\Selenium Software\\edgedriver_win64\\msedgedriver.exe");
			driver=new EdgeDriver();
			driver.get("https://demowebshop.tricentis.com/");
			driver.manage().window().maximize();

		}

		else {
			System.out.println("Browser is not valid");
		}

	}

	@Test
	public void loginChrome() {

		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys("vijiraja@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("viji123");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		boolean displayed = driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed();
		Assert.assertTrue(displayed);
		driver.findElement(By.xpath("//a[text()='Log out']")).click();
		System.out.println("Login chrome browser done");
		
		driver.quit();


	}
}
