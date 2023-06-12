package demoAutomation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultiSelectDropdown {

	WebDriver driver;

	@BeforeMethod
	public void launch() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Accordion.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test(priority=1)
	public void multipleSelectDropdown() throws InterruptedException {
		//navigate to autocomplete in widgets tab
		Actions builder=new Actions(driver);
		WebElement widget = driver.findElement(By.xpath("//a[text()='Widgets']"));
		WebElement autocomplete = driver.findElement(By.xpath("//a[text()=' AutoComplete ']"));
		builder.moveToElement(widget).moveToElement(autocomplete).click().build().perform();
		//	try {
		if(driver.findElement(By.xpath("//iframe[@id='aswift_5']")).isDisplayed()){
			//switch to the ad frame and click dismiss
			WebElement parFrame = driver.findElement(By.xpath("//iframe[@id='aswift_5']"));
			driver.switchTo().frame(parFrame);
			WebElement chiFrame = driver.findElement(By.xpath("//iframe[@id='ad_iframe']"));
			driver.switchTo().frame(chiFrame);
			driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
			//driver.switchTo().defaultContent();
			//Alert alert=driver.switchTo().alert();
			//alert.dismiss();
			System.out.println("Unexpected ad handled");
		}
		//catch(Exception e)
		else
		{
			System.out.println("unexpected ad not present");
		}

		//validation for ascending order dropdown
		driver.findElement(By.id("searchbox")).sendKeys("i");
		List<WebElement> list = driver.findElements(By.className("ui-menu-item"));
		System.out.println(list.size());
		//all the text of dropdownlist are stored in a list
		List<String> ddlist=new ArrayList<String>(); 
		for(int i=0;i<list.size();i++)
		{
			ddlist.add(list.get(i).getText());
		}

		Thread.sleep(3000);
		List<String> originalist=ddlist;
		System.out.println(originalist);
		Collections.sort(ddlist);
		Thread.sleep(3000);
		System.out.println(ddlist);

		if(originalist.equals(ddlist)) {
			System.out.println("List is sorted");
		}else {
			System.out.println("List is not sorted");
		}


		//add multiple options from dropdown
		driver.findElement(By.id("searchbox")).sendKeys("india");
		//feeze dom and get the dropdown option and then select-- setTimeout(function(){debugger;}, 5000)
		driver.findElement(By.xpath("//a[text()='India']")).click();
		driver.findElement(By.id("searchbox")).sendKeys("bangladesh");
		driver.findElement(By.xpath("//a[text()='Bangladesh']")).click();
		driver.findElement(By.id("searchbox")).sendKeys("china");
		driver.findElement(By.xpath("//a[text()='China']")).click();
		//validation
		System.out.println("Mutiple select dropdown is verified as follows :");
		List<WebElement> input = driver.findElements(By.xpath("//div[@class='ui-autocomplete-multiselect-item']"));
		for(WebElement each:input) {
			String text = each.getText();
			//if("IndiaBangladeshChina".contains(text))
			if(text.contains("India")||
					text.contains("Bangladesh")||
					text.contains("China"))
			{
				System.out.println(text);
			}
		}



	}

}
