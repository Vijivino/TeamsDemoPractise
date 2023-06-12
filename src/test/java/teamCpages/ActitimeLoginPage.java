package teamCpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class ActitimeLoginPage {
	
	WebDriver driver;
	
	@FindBy(id="username") WebElement username;
	@FindBy(name="pwd") WebElement password;
	@FindBy(name="remember") WebElement chBox;
	@FindBy(id="loginButton") WebElement loginBtn;
	
	
	public ActitimeLoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterNamePW(String uname,String pword) {
		
		username.sendKeys(uname);
		password.sendKeys(pword);
	}
	
	public void enabled() {
		boolean checkboolean = chBox.isEnabled();
		SoftAssert Sassert=new SoftAssert();
		Sassert.assertTrue(checkboolean);
		Sassert.assertAll();
		System.out.println("logged in checkbox enabled -- Status "+checkboolean);
	}
		
	public void clickLogin() {
		loginBtn.click();
		driver.quit();
	}
	
		
	
}
