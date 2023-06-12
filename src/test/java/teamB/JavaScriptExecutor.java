package teamB;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptExecutor {

	WebDriver driver;
	@BeforeMethod
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().window().maximize();

	}

	@Test
	public void useOfJavaScript() {

		// to scroll down the page
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");

		//enter values with js
		WebElement fname = driver.findElement(By.id("firstName"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].value='vijayalaxmi'", fname);
		//highlight with colour
		executor.executeScript("arguments[0].style.background='green'", fname);

		//validation for checking the colour
		String colour = driver.findElement(By.xpath("//input[@style='background: green;']")).getAttribute("style");
		Assert.assertEquals(colour, "background: green;");
		System.out.println("Highlight colour is verified "+colour);

        //enter lastname
		WebElement lname = driver.findElement(By.id("lastName"));
		executor.executeScript("arguments[0].value='Raja'", lname);

		//colour the border
		executor.executeScript("arguments[0].style.border='3px solid red'", lname);

		//click button 
		WebElement button = driver.findElement(By.xpath("//label[text()='Female']"));
		executor.executeScript("arguments[0].click()", button);

		//validation button enabled
		boolean enabled = button.isEnabled();
		Assert.assertTrue(enabled);
		System.out.println("Radio button is enabled");


	}

}
