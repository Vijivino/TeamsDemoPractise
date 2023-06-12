package teamA;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeadlessBrowser {

	WebDriver driver;

	@BeforeMethod
	public void launch() throws IOException {

		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\TeamDemoPractise\\src\\test\\resources\\configHeadless.properties");
		prop.load(fis);
		String Browser = prop.getProperty("browser");
		System.out.println(Browser);

		if(Browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions opt=new ChromeOptions();
			opt.addArguments("--headless=new");
			driver=new ChromeDriver(opt);
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
		}	
		else if(Browser.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver","C:\\Selenium Software\\edgedriver_win64\\msedgedriver.exe");
			EdgeOptions opt=new EdgeOptions();
			//opt.setHeadless(true);
			opt.addArguments("--headless=new");
			driver=new EdgeDriver(opt);
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();

		}
	}

	@Test
	public void headlessrun() throws IOException {

		//Validating the browser opened
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertTrue(title.contains("Amazon"));         

	}

}
