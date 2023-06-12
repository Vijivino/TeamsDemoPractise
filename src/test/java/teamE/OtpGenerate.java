package teamE;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OtpGenerate {
	
	WebDriver driver;
	
	@Test
	public void OTPGen() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://selfregistration.cowin.gov.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//enter the mobNo in the console using system.in
		Scanner sc = new Scanner(System.in);
		String mobNo=sc.next(); //store the entered mob no in the string
		
		//pass the mobNo to the application
		driver.findElement(By.id("mat-input-0")).sendKeys(mobNo);
		
		//click generate otp
		driver.findElement(By.tagName("ion-button")).click();
		
		//enter the otp in console and save it in string
		String otp = sc.next();
		
		driver.findElement(By.id("mat-input-1")).sendKeys(otp);
		
		driver.findElement(By.tagName("ion-button")).click();
		
		
		Thread.sleep(3000);
		boolean displayed = driver.findElement(By.xpath("//*[@id=\"main-content\"]/app-welcome/app-header/ion-grid/ion-row[1]/ion-col/ion-row/ion-col[2]/div/ul/li")).isDisplayed();
		Assert.assertTrue(displayed);
		//*[@id="main-content"]/app-welcome/app-header/ion-grid/ion-row[1]/ion-col/ion-row/ion-col[2]/div/ul/li
		
		
	}

}
