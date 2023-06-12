package parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest {

	WebDriver driver;

	@Given("launch the browser with login page")
	public void launch() {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();

	}

	@When("User enters email and password")
	public void user_enters_email_and_password() {

		driver.findElement(By.id("Email")).sendKeys("vijiraja@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("viji123");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();

	}

	@Then("User should verify the login")
	public void user_should_verify_the_login() {

		boolean displayed = driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed();
		Assert.assertTrue(displayed);

		driver.quit();

	}


}
