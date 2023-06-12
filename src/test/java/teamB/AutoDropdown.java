package teamB;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoDropdown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.airindia.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");

		driver.findElement(By.id("from")).click();
		driver.findElement(By.id("from")).sendKeys("pa");

		//store all the options listed in a list
		List<WebElement> options = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));

		//iterate each and get the text 	
		for(WebElement each:options) //enhanced for loop
		{
			if(each.getText().contains("Paris")) 
			{
				each.click();
				break;
			}

		}

		//validation
		String value = driver.findElement(By.id("hfFrom")).getAttribute("value");
		if(value.contains("Paris"))
		{
			System.out.println("The dropdown is selected for  ---- "+value);
		}


		//driver.quit();
	}

}
