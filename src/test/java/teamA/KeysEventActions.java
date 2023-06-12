package teamA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class KeysEventActions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demoqa.com/text-box");
		driver.manage().window().maximize();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		
		//enter the currentaddress
		driver.findElement(By.id("currentAddress")).sendKeys("123, Nehru street, Tamilnadu");
		String currentAd = driver.findElement(By.id("currentAddress")).getAttribute("value");
		
		Actions builder = new Actions(driver);
		//select the currentaddress and copy
		builder.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		builder.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
		//move to next permanent address and paste
		builder.sendKeys(Keys.TAB).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();	
		
		String permanentAd = driver.findElement(By.id("permanentAddress")).getAttribute("value");
		
		
		Assert.assertEquals(currentAd, permanentAd);
		System.out.println("Permanent and current address are same");
		
		WebElement element = driver.findElement(By.id("currentAddress"));
		builder.contextClick(element).perform();
		
		
		//driver.quit();
		

	}

}
