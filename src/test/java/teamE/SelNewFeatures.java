package teamE;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelNewFeatures {

	WebDriver driver;

	@Test
	public void relativeLocators() throws IOException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.name("username")).sendKeys("Admin");

		//Accessing the element with relative locators
		WebElement loginbtn = driver.findElement(By.xpath("//button[@type='submit']"));
		driver.findElement(RelativeLocator.with(By.tagName("input")).above(loginbtn)).sendKeys("admin123");

		loginbtn.click();

		WebElement dashboard = driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']"));
		//take screenshot of Performance webelemnt
		takepic(dashboard,"dashboardSnap");

		//move to my info tab by using rel locator
		driver.findElement(RelativeLocator.with(By.tagName("li")).above(dashboard)).click();

		//to zoom in and zoom out
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		Thread.sleep(2000);
		executor.executeScript("document.body.style.zoom = '200%'");
		Thread.sleep(2000);
		executor.executeScript("document.body.style.zoom = '100%'");
		
		//to open a new blank window
		driver.switchTo().newWindow(WindowType.WINDOW);
		System.out.println("It is a blank window with title as "+driver.getTitle());
		Set<String> windowHandles = driver.getWindowHandles();
        List<String> list=new ArrayList<String>(windowHandles);
        System.out.println(list.size());
		driver.close();
		
		//switch the control back
        driver.switchTo().window(list.get(0));

		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
		driver.quit();

	}

	private void takepic(WebElement ele, String filename) throws IOException {
		// TODO Auto-generated method stub
		File source = ele.getScreenshotAs(OutputType.FILE);
		File destiny=new File("C:\\Selenium Software\\SelNewFeaturesSnaps\\"+filename+".png");
		FileUtils.copyFile(source, destiny);
	}
}
