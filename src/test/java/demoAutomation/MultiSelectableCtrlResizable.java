package demoAutomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultiSelectableCtrlResizable {

	WebDriver driver;

	@BeforeMethod
	public void launch() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Selectable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void MultiselectWithCtrl() {

		driver.findElement(By.xpath("//a[text()='Serialize ']")).click();
		
		//select multiple elements using ctrl key
		WebElement first = driver.findElement(By.xpath("//div[@id='Serialize']//b[contains(text(),'Single Line Coding')]"));
		WebElement second = driver.findElement(By.xpath("//div[@id='Serialize']//b[contains(text(),'Method Chaining')]")); 
		WebElement third = driver.findElement(By.xpath("//div[@id='Serialize']//b[contains(text(),'Functional Test')]"));
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.LEFT_CONTROL)
		.click(first)
		.click(second)
		.click(third)
		.keyUp(Keys.LEFT_CONTROL)
		.build()
		.perform();
		
		//validation 
		List<WebElement> list = driver.findElements(By.xpath("//li[@class='ui-widget-content selected']"));
	  
		for(WebElement each:list) {
	    	if(each.getText().equals(first.getText()) ||
	    			each.getText().equals(second.getText()) || 
	    			 each.getText().equals(third.getText())) 
	    	{
	    		System.out.println(each.getText());
	    		//break;
	    	}
	   }
		
	}


}
