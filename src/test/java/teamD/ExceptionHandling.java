package teamD;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ExceptionHandling {
	
	@Test
	public void timeOutException()
	{
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
		driver.get("https://demowebshop.tricentis.com/");
		driver.quit();
	}
	
	@Test
	public void noSuchSessionException()
	{
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		//driver.findElement(By.linkText("Register")).click();
		driver.quit();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test
	public void noalert() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().window().maximize();
	
		driver.findElement(By.xpath("//button[contains(text(),'click the button to display an')]")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Alert alert1 = driver.switchTo().alert();
	}

	

}
