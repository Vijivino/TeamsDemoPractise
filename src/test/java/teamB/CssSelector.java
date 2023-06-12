package teamB;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CssSelector {

	@Test
	public void cssSelector() {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//using id attribute use #
		//driver.findElement(By.cssSelector("input[id='twotabsearchtextbox'])")).sendKeys("mobile",Keys.ENTER);
		///driver.findElement(By.cssSelector("input#twotabsearchtextbox")).sendKeys("mobile",Keys.ENTER);
		driver.findElement(By.cssSelector("input[id*='bsearchte'")).sendKeys("mobile",Keys.ENTER);
		//driver.findElement(By.cssSelector("input[id^='twotab'")).sendKeys("mobile",Keys.ENTER);
		//driver.findElement(By.cssSelector("input[id$='textbox'")).sendKeys("mobile",Keys.ENTER);
		
		// to scroll down the page
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		String title1= driver.getTitle();
		
	
		//Validation using class attribute use .
		driver.findElement(By.cssSelector("img[class='s-image']")).click();
		String title2 = driver.getTitle();
		Assert.assertEquals(title1, title2);
		System.out.println("Mobile added by css selector ");
		
		driver.quit();

	}
}
