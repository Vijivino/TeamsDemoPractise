package teamE;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HiddenElement {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.letskodeit.com/practice");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//scroll down 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");

		//enter value in textbox
		WebElement textbox = driver.findElement(By.id("displayed-text"));
		driver.findElement(By.id("displayed-text")).sendKeys("selenium");
		Thread.sleep(1000);
		//hide the textbox
		driver.findElement(By.id("hide-textbox")).click();

		//change the value as java from hidden textbox using js
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		//jse.executeScript ("arguments[0].setattribute('style','display: none')",textbox);
		jse.executeScript("document.getElementById('displayed-text').value='java';");

		//to get the value from the hidden element
		String s = (String) jse.executeScript("return document.getElementById('displayed-text').value");
		System.out.println("Value changed in the hidden textbox as  "+s);

		//show the hidden textbox again
		driver.findElement(By.id("show-textbox")).click();
		//jse.executeScript ("arguments[0].setattribute('style','display: block')",textbox);




	}

}
