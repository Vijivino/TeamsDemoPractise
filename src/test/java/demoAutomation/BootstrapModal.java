package demoAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BootstrapModal {
	
	WebDriver driver;

	@BeforeMethod
	public void launch() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	
	}
	
	@Test 
	public void checkLoader() throws InterruptedException {
		
		driver.get("https://demo.automationtesting.in/Loader.html");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("loader")).click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(90));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save changes']")));
		
		//switch the control to modal dialog box
		driver.switchTo().activeElement();
		//valiadte the modal alert display
		boolean disp = driver.findElement(By.className("modal-body")).isDisplayed();
		System.out.println(disp);
		Assert.assertTrue(disp);
		//print the content inside the modal alert
		String content = driver.findElement(By.className("modal-body")).getText();
		System.out.println(content);
		Assert.assertNotNull(content);
		Thread.sleep(2000);
		//close the modal alert
		driver.findElement(By.xpath("//button[text()='Save changes']")).click();
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[text()='Save changes']")));
		
		boolean displayed = driver.findElement(By.id("loader")).isDisplayed();
		Assert.assertTrue(displayed);
	}
	
	
	@Test
	public void handleModals() throws InterruptedException {
		
		driver.get("https://demo.automationtesting.in/Modals.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 
		driver.findElement(By.xpath("(//a[text()='Launch modal'])[2]")).click();
		Thread.sleep(2000);
		//handling first modal
		driver.switchTo().activeElement();
		boolean disp = driver.findElement(By.xpath("//div[@id='myModalMulti']//div[@class='modal-body']")).isDisplayed();
		System.out.println(disp);
		Assert.assertTrue(disp);
		String content = driver.findElement(By.xpath("//div[@id='myModalMulti']//div[@class='modal-body']")).getText();
		System.out.println(content);
		Assert.assertNotNull(content);
		driver.findElement(By.xpath("//div[@class='modal-body']/a[text()='Launch modal']")).click();
		Thread.sleep(3000);
		//closing the modal2
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("(//a[text()='Close'])[2]")).click();
		Thread.sleep(3000);
		//closing the modal 1
		driver.findElement(By.xpath("//a[text()='Close']")).click();
		
		boolean displayed = driver.findElement(By.xpath("//a[text()='Launch modal']")).isDisplayed();
		Assert.assertTrue(displayed);
		
	}

}
