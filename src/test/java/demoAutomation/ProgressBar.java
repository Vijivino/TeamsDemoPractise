package demoAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ProgressBar {
	
	WebDriver driver;
	Actions a;
	@Test(enabled=true)
	public void progress() throws InterruptedException
	{
		driver= new ChromeDriver();
		driver.get("https://demo.automationtesting.in/ProgressBar.html");
		driver.manage().window().maximize();
		//Before Download 
		WebElement style_element =driver.findElement(By.xpath("(//*[local-name()='svg']/*[name()='path'])[last()]"));
		String beforeStyle= style_element.getCssValue("stroke-dashoffset");
		//*[@id="container"]/svg/path[2]
		System.out.println("Before download Stroke-dashoffset value " + beforeStyle);
		//click on download
		driver.findElement(By.xpath("//button[@class='btn btn-block btn-primary active']")).click();
		WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(90));
		//validating the percent number
		WebElement percent = driver.findElement(By.cssSelector("div.progressbar-text"));
		
		wt.until(ExpectedConditions.textToBePresentInElement(percent,"100"));
		
		String percent_text =percent.getAttribute("innerText");
		//printing the rgb value of Text
		String color_percent= percent.getCssValue("color");
		System.out.println("Text color of 100 is :" + color_percent);
		//validation
		if(percent_text.equalsIgnoreCase("100"))
		{
			System.out.println("download completed");

		}
		else
		{
			System.out.println("download not successfull");
		}
		
		
		WebElement style_element2 =driver.findElement(By.xpath("(//*[local-name()='svg']/*[name()='path'])[last()]"));
		wt.until(ExpectedConditions.attributeToBe(style_element2, "style", "stroke-dasharray: 295.416, 295.416; stroke-dashoffset: 0;"));
		//after download the progress bar style
	
		String afterStyle = style_element2.getCssValue("stroke-dashoffset");
		System.out.println("After download Stroke-dashoffset value "+afterStyle);
		//Validating
		if(beforeStyle.equalsIgnoreCase(afterStyle))
			
		{
			System.out.println("progress color not changed");
			
		}
		else
		{
			System.out.println("Progress color changed");
		}
}
	@AfterMethod
	public void close()
	{
		//driver.quit();
	}

}
