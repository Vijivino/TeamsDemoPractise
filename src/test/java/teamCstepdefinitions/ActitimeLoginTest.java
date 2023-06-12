package teamCstepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import teamCpages.ActitimeLoginPage;

public class ActitimeLoginTest {

	WebDriver driver;
	ActitimeLoginPage login;
	
	@Given("Launch Browser")
	public void launch_browser() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://demo.actitime.com/login.do");
		driver.manage().window().maximize();
	   
	}
	
	@When("Enter username and password from {string} and {int}")
	public void enter_username_and_password(String sheetname,Integer rownum) throws IOException {
		
		login=PageFactory.initElements(driver, ActitimeLoginPage.class );
		
		File credentials=new File("C:\\Selenium Software\\Book1.xlsx");
		FileInputStream credeObj=new FileInputStream(credentials);
		XSSFWorkbook exfile=new XSSFWorkbook(credeObj);
		XSSFSheet sheet1=exfile.getSheetAt(0);
		//int rowNum = sheet1.getLastRowNum();

		String uname = sheet1.getRow(rownum).getCell(0).getStringCellValue();
		String pword=sheet1.getRow(rownum).getCell(1).getStringCellValue();
		System.out.println(uname +" "+pword);
		login.enterNamePW(uname,pword);
		 
	}
	@When("Click login")
	public void click_login() {
	    
		login.enabled();
		login.clickLogin();
	}
	
	
	
	@Then("User should see logout")
	public void user_should_see_logout() {
		
		
	   
	}


}
