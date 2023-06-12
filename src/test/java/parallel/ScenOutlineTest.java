package parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ScenOutlineTest {
	
	WebDriver driver;
	
	@Given("launch the browser")
	public void launch_the_browser() {
	   
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();
		
	}
	@When("^User enters the (.*) and  (.*)$")
	public void user_enters_the_email_and_password(String email,String password) {
	   
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
	}
	@Then("User should see the login")
	public void user_should_see_the_login() {
	    
		boolean displayed = driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed();
		Assert.assertTrue(displayed);

		driver.quit();
		
	}


}
