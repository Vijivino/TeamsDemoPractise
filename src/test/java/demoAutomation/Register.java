package demoAutomation;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Register {

	WebDriver driver;

	@BeforeMethod
	public void launc() {

		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Register.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}


	@Test
	public void registerdetails() {

		//enter name
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("vijayalaxmi");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("R");
		//address
		driver.findElement(By.xpath("//textarea[@ng-model='Adress']")).sendKeys("chennai");
		//Radio button
		driver.findElement(By.xpath("//input[@value='FeMale']")).click();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("viji@gmail.com");
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("1234567890");
		//select all checkboxes
		List<WebElement> all = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for(WebElement each:all) {
			each.click();
		}

		//Country Mandatory
		driver.findElement(By.id("countries"));//cant enter the value so should validate the error message
		//select country
		Select sel1=new Select(driver.findElement(By.id("country")));
		sel1.selectByValue("India");

		//multiselect dropdown language
		driver.findElement(By.id("msdd")).click();
		List<WebElement> list = driver.findElements(By.xpath("//ul/li[@class='ng-scope']"));
		System.out.println("no.of languages in multiselect dropdown "+list.size());
		for(int i=0;i<list.size();i++) {
			//System.out.println(list.get(i).getText());
			if(list.get(i).getText().equalsIgnoreCase("english") ||
					list.get(i).getText().equalsIgnoreCase("spanish") ||
					list.get(i).getText().equalsIgnoreCase("hindi"))
			{
				list.get(i).click();
				//break;
			}
		}

		//Select dropdown skills
		WebElement select = driver.findElement(By.id("Skills"));
		Select sel=new Select(select);
		sel.selectByVisibleText("Certifications");

		//Select year,month,day
		WebElement year = driver.findElement(By.xpath("//select[@placeholder='Year']"));
		Select yea=new Select(year);
		yea.selectByValue("1930");

		WebElement month = driver.findElement(By.xpath("//select[@placeholder='Month']"));
		Select mon=new Select(month);
		mon.selectByVisibleText("May");

		WebElement day = driver.findElement(By.xpath("//select[@placeholder='Day']"));
		Select dayobj=new Select(day);
		dayobj.selectByIndex(2);

		driver.findElement(By.id("firstpassword")).sendKeys("viji123");
		driver.findElement(By.id("secondpassword")).sendKeys("viji123");
		driver.findElement(By.id("submitbtn")).click();
		
		//upload the photo
		driver.findElement(By.id("imagesrc")).sendKeys("C:\\Selenium Software\\snaplogin.png");
		
		//**********validation for getting the validation message that will disappear shortly
		String text = driver.findElement(By.id("countries")).getAttribute("validationMessage");
		Assert.assertNotNull(text);
		System.out.println("Validation msg is captured as "+text);	

		//driver.quit();

	}

}
