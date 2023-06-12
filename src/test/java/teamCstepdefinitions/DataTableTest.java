package teamCstepdefinitions;


import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataTableTest {

	WebDriver driver;

	@Given("User is on demoshop registration page")
	public void user_is_on_demoshop_registration_page() {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/register");
		driver.manage().window().maximize();

	}
	@When("User enters following details with headers")
	public void user_enters_following_details_with_headers(DataTable dataTable) throws InterruptedException{

		//create datatable and pass as arguments
		List<Map<String, String>> DataMap =dataTable.asMaps(String.class, String.class);
		System.out.println(DataMap);
		System.out.println(DataMap.size());

		//pass the table in loop and map the content with header for each data set
		for(int i=0;i<DataMap.size();i++) {

			driver.findElement(By.name("FirstName")).clear();
			driver.findElement(By.name("FirstName")).sendKeys(DataMap.get(i).get("firstname"));

			driver.findElement(By.id("LastName")).clear();
			driver.findElement(By.id("LastName")).sendKeys(DataMap.get(i).get("lastname"));

			driver.findElement(By.id("Email")).clear();
			driver.findElement(By.id("Email")).sendKeys(DataMap.get(i).get("email"));

			driver.findElement(By.name("Password")).clear();
			driver.findElement(By.name("Password")).sendKeys(DataMap.get(i).get("password"));

			driver.findElement(By.id("ConfirmPassword")).clear();
			driver.findElement(By.id("ConfirmPassword")).sendKeys(DataMap.get(i).get("confpass"));
			// System.out.println(userMap.get("firstname"));
			driver.findElement(By.id("register-button")).click();

		}

	}
	@Then("User can see registration is successful")
	public void user_can_see_registration_is_successful() {
		
	
		String errorText = driver.findElement(By.xpath("//li[text()='The specified email already exists']")).getText();
		if (errorText.contains("already exists")) {

			System.out.println("User has already registered");
		} else {

			System.out.println("Registration is successful");
		}

     driver.close();
		
	}


}
