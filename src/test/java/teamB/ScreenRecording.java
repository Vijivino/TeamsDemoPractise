package teamB;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScreenRecording {

	WebDriver driver;
	
	@Test
	public void selectValues() throws Exception {
		
		//calling the utility class method to start record
		ScreenRecorderUtil.startRecord("login orangeHRM");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");  
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//validation
		boolean displayed = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).isDisplayed();
		Assert.assertTrue(displayed);
		
		driver.quit();
		
		//call the method from utility class to end the recording
		ScreenRecorderUtil.stopRecord();
		

	}

}
