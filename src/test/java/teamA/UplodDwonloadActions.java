package teamA;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class UplodDwonloadActions {

	@Test
	public void uploadDownload() {
		// TODO Auto-generated method stub

		//System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demoqa.com/upload-download");
		driver.manage().window().maximize();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");

		Actions builder=new Actions(driver);

		//download a static file written already
		WebElement download = driver.findElement(By.id("downloadButton"));
		builder.click(download).perform();
		
		
		//uplaod a file from my computer
		WebElement upload = driver.findElement(By.id("uploadFile"));
		upload.sendKeys("C:\\Users\\vijayalaxmi.rajaa\\OneDrive - HCL Technologies Ltd\\Documents\\pom.txt");
		builder.build().perform();
		System.out.println("Upload and download done");
		//driver.quit();

	}
	
	@Test(priority=1)
	public void fileUpload() {
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.hcltech.com/contact-us/customer");
		driver.manage().window().maximize();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,550)");
		
		WebElement upload = driver.findElement(By.xpath("//input[@name='files[upload_multifile][]']"));
		upload.sendKeys("C:\\Users\\vijayalaxmi.rajaa\\OneDrive - HCL Technologies Ltd\\Documents\\pom.txt");
		
	}
	
	@Test(priority=2)
	public void dynamicFileDownload() {
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/FileDownload.html");
		driver.manage().window().maximize();
		
		//enter values and create our own file
		driver.findElement(By.id("textbox")).sendKeys("Selenium is an open-source tool that automates web browsers.");
		driver.findElement(By.id("createTxt")).click();
		
		Actions builder=new Actions(driver);
		builder.sendKeys(driver.findElement(By.id("link-to-download"))).click().perform();
		
		System.out.println("Dynamic Selenium file download is done");
		
		}
	@Test(priority=3)
	public void downloadChromeOptions() {
		
		
		//downloading file using chromeoptions#object of Options class
		ChromeOptions op =new ChromeOptions();
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("plugins.plugins_disabled", new String[] {"Chrome PDF Viewer"});
		prefs.put("plugins.always_open_pdf_externally", true);
		prefs.put("download.default_directory", "C:\\Users\\vijayalaxmi.rajaa\\Downloads");
		//prefs.put("download.prompt_for_download", false);
		op.setExperimentalOption("prefs", prefs);
		
		WebDriver driver=new ChromeDriver(op);
		
		driver.get("https://www.jio.com/selfcare/plans/mobility/ir-plans-details/?selectedCountries=US");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement ele = driver.findElement(By.xpath("(//button/div[text()='Standard PayGo Rates'])[2]"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele);
		//driver.findElement(By.xpath("(//button/div[text()='Standard PayGo Rates'])[2]")).click();
		
	}

}
