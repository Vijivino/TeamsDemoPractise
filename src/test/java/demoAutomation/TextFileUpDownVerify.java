package demoAutomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextFileUpDownVerify {
	
	WebDriver driver;
	public String input="Selenium is an open-source tool that automates web browsers.";
	
	@Test
	public void dynamicFileDownloadVerify() throws InterruptedException {
		
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/FileDownload.html");
		driver.manage().window().maximize();
		
		//enter values and create our own file
		driver.findElement(By.id("textbox")).sendKeys(input);
		driver.findElement(By.id("createTxt")).click();
		
		Actions builder=new Actions(driver);
		builder.sendKeys(driver.findElement(By.id("link-to-download"))).click().perform();
		System.out.println("Dynamic Selenium file download is done");
		Thread.sleep(3000);
		
		//validating the text with the given text
		//get the downloaded text file
		driver.navigate().to("C:\\Users\\vijayalaxmi.rajaa\\Downloads\\info.txt");
		//read the source content
		String source = driver.getPageSource();
		System.out.println(source);
		Assert.assertTrue(source.contains(input));
		System.out.println("Content is verified");
		
			
		}
	

@Test(description = "File Uploading",priority = 1)
public void FileUploadVerify() throws AWTException, IOException  {
	driver = new ChromeDriver();
	driver.get("https://demo.automationtesting.in/FileUpload.html");	
	driver.manage().window().maximize();

	
	WebElement ele = driver.findElement(By.xpath("//input[@id='input-4']"));
	Actions act = new Actions(driver);
	act.moveToElement(ele).click().perform();

	//upload using robot
	StringSelection stringselection=new StringSelection("C:\\Users\\vijayalaxmi.rajaa\\Downloads\\mock.txt");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection,null);

	Robot robo = new Robot();
	robo.delay(2000);

	robo.keyPress(KeyEvent.VK_CONTROL);
	robo.keyPress(KeyEvent.VK_V);

	robo.keyRelease(KeyEvent.VK_CONTROL);
	robo.keyRelease(KeyEvent.VK_V);

	robo.keyPress(KeyEvent.VK_ENTER);
	robo.keyRelease(KeyEvent.VK_ENTER);
	System.out.println("Uploaded the file");
	robo.delay(2000);
	
	//reading data from  after upload
	String uploadedText = driver.findElement(By.xpath("//textarea[@title='mock.txt']")).getText();
	System.out.println(uploadedText);
	//write into text file again to compare
	String TestFile = "C:\\Users\\vijayalaxmi.rajaa\\OneDrive - HCL Technologies Ltd\\Documents\\uploded.txt";
	File uplodedFile = new File(TestFile);//Created object of java File class.
	uplodedFile.createNewFile();
    FileWriter FW = new FileWriter(TestFile);
	BufferedWriter BW = new BufferedWriter(FW);
	BW.write(uploadedText);
    BW.close();
	
    //get the source file 
	File sourceFile=new File("C:\\Users\\vijayalaxmi.rajaa\\Downloads\\mock.txt");
	
	//compare the two files
	boolean b = FileUtils.contentEqualsIgnoreEOL(uplodedFile, sourceFile,null);
	Assert.assertTrue(b);
	
	System.out.println("The uploaded file data is verified");

	
}

}
