package demoAutomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InteractionsTab {
	
	WebDriver driver;
	

	@Test
	public void dragAndDrop() throws InterruptedException, AWTException {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Dynamic.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,150)");
		
		WebElement source = driver.findElement(By.xpath("//img[@id='angular']"));
		WebElement destin = driver.findElement(By.id("msg"));
		Thread.sleep(2000);
		Actions builder=new Actions(driver);
		//builder.dragAndDrop(source, destin).perform();
		//builder.dragAndDropBy(source, 541, 174).build().perform();
		builder.clickAndHold(source).moveToElement(source, 600, 0).release().perform();
		//builder.clickAndHold(source).moveToElement(destin).release().perform();
		//builder.clickAndHold(source).moveByOffset(541, 0).perform();
//		Action dragAndDrop =
//				builder.clickAndHold(source).moveToElement(destin).release(destin).build();
//				Thread.sleep(2000);
//				dragAndDrop.perform();
		Rectangle rect = destin.getRect();
		System.out.println(rect.getY());
		int a = source.getLocation().x;
        int b = source.getLocation().y;
		int x = destin.getLocation().x;
        int y = destin.getLocation().y;
        System.out.println(a +" - "+b);
         System.out.println(x +" - "+y);
      
 /*       builder.moveToElement(source)
                .pause(Duration.ofSeconds(1))
                .clickAndHold(source)
                .pause(Duration.ofSeconds(1))
                .moveByOffset(a, b)
                .moveToElement(destin)
                .moveByOffset(x,y)
                .pause(Duration.ofSeconds(1))
                .release().build().perform();
     
 /*       Robot robot = new Robot();           
        robot.mouseMove(a, b);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(x, y);
        Thread.sleep(2000);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(2000);
  */      
        
	}
	
	@Test(priority=1)
	public void dragDropStatic() throws InterruptedException {
		
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Static.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement source = driver.findElement(By.xpath("//img[@id='node']"));
		WebElement destin = driver.findElement(By.id("droparea"));
		Thread.sleep(2000);
		Actions a=new Actions(driver);
		//a.dragAndDrop(source, destin).perform();
		a.clickAndHold(source).moveToElement(destin).release().perform();
		
		
		
		
		
	}

}
