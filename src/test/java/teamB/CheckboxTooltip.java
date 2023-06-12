package teamB;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckboxTooltip {

	@Test
	public void checkboxTooltip() {

		//Launch browser
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Register.html");
		driver.manage().window().maximize();
		

		//get the text of tooltip
		WebElement email = driver.findElement(By.xpath("//*[@type='email']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(email).perform();
		String tooltip = driver.findElement(By.xpath("//*[@type='email']//following::span[contains(text(),'valid email')]")).getText();
		Assert.assertEquals(tooltip, "Provide a valid email id for further updates");
		System.out.println(tooltip);

		//select all the checkboxes at a time
		List<WebElement> all = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for(WebElement each:all) {
			each.click();
		}

		//validating
		List<WebElement> all1 = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for(WebElement each1:all1) {
			boolean enabled = each1.isEnabled();
			//soft assert so that it will continue the iteration even if it fails
			SoftAssert sAssert=new SoftAssert();
			sAssert.assertTrue(enabled);
			sAssert.assertAll();
			String checkbox = each1.getAttribute("value");
			System.out.println(checkbox+" enabled status "+enabled);

			//driver.quit();
		}

	}



}
