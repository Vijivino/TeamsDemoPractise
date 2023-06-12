package demoAutomation;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WidgetsCollapSlider {

	WebDriver driver;

	@BeforeMethod
	public void launch() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Accordion.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void accordian() throws InterruptedException {
		//validate the collapsible content 1
		//driver.findElement(By.id("collapse1")).click();
		String content = driver.findElement(By.xpath("//div[@id='collapse1']/div")).getText();
		Assert.assertNotNull(content);
		System.out.println("Content 1 is displayed as : "+content);
		Thread.sleep(3000);

		//content 4
		driver.findElement(By.xpath("//b[contains(text(),'4')]")).click();
		Thread.sleep(3000);
		String content2 = driver.findElement(By.xpath("//div[@id='collapse4']/div")).getText();
		Assert.assertNotNull(content2);
		System.out.println("Content 4 is displayed as : "+content2);
	}

	@Test(priority=1,description="check the slider")
	public void moveSlider() throws InterruptedException {

		Actions builder=new Actions(driver);
		WebElement widget = driver.findElement(By.xpath("//a[text()='Widgets']"));
		WebElement slidertab= driver.findElement(By.xpath("//a[text()=' Slider ']"));
		builder.moveToElement(widget).moveToElement(slidertab).click().build().perform();


		if(driver.findElement(By.xpath("//iframe[@id='aswift_5']")).isDisplayed()) 
		{
			//switch to the ad frame and handle two frames and then click dismiss 
			WebElement parFrame = driver.findElement(By.xpath("//iframe[@id='aswift_5']"));
			driver.switchTo().frame(parFrame);
			WebElement chiFrame = driver.findElement(By.xpath("//iframe[@id='ad_iframe']"));
			driver.switchTo().frame(chiFrame);
			driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
			//driver.switchTo().defaultContent();
			System.out.println("Unexpected ad handled");
		}
		else
		{
			System.out.println("unexpected ad not present");
		}

		//to move the slider
		WebElement slider = driver.findElement(By.xpath("//div[@id='slider']/a"));
		//using js
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].setAttribute('style', 'left: 30%;')",slider);
		//using actions class
		builder.dragAndDropBy(slider, 300, 0).perform();

		//scroll till the webelement
		WebElement rightsMsg = driver.findElement(By.xpath("//a[text()='Automation Testing']"));
		executor.executeScript("arguments[0].scrollIntoView(true);", rightsMsg);
		//executor.ExecuteScript("arguments[0].scrollIntoViewIfNeeded(true);", rightsMsg);
        Thread.sleep(3000);
		//again scroll back to slider by getting the position
		Rectangle rect = slider.getRect();
		System.out.println("x offset "+rect.x +","+ "y offset "+rect.y);
		System.out.println("width "+rect.width+","+"height "+rect.height);
		//executor.executeScript("window.scrollBy(0,281)", slider);
		executor.executeScript("arguments[0].scrollIntoView(true);", slider);
}


	@AfterMethod
	public void close() {

		//driver.quit();
	}
}
