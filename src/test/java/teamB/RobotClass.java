package teamB;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class RobotClass {

	@Test
	public void robotFunctions() throws AWTException {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver(ops);
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		Robot robo=new Robot();
		// to scroll up and down
		robo.mouseWheel(80);
		System.out.println("scrolled up");
		robo.mouseWheel(-90);
		System.out.println("scrolled down");

		//move to x and y coordinate
		robo.mouseMove(395,585);
		//like thread.sleep
		robo.delay(2000);

		//mouse click by press and release
		//to press mouse left button
		robo.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		//to release mouse left button
		robo.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		driver.quit();

	}

	@Test(priority=1)
	public void TakescreenshotRobot() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://demoqa.com/upload-download");
		driver.manage().window().maximize();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");

		Actions builder=new Actions(driver);
		//download a static file written already
		WebElement download = driver.findElement(By.id("downloadButton"));
		builder.click(download).perform();
		Thread.sleep(3000);

		try{
			// Creating Robot class object
			Robot robotClassObject = new Robot();
			// Get screen size
			Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			// Capturing screenshot by providing size
			BufferedImage tmp = robotClassObject.createScreenCapture(screenSize); 
			// Defining destination file path
			String path=System.getProperty("user.dir")+"/ScreenCapturesPNG/snap"+System.currentTimeMillis()+".png";
			// To copy temp image in to permanent file
			ImageIO.write(tmp, "png",new File(path)); 
		}catch(Exception e)
		{
			System.out.println("Some exception occured." + e.getMessage());
		}
	}

	@Test(priority=2)
	public void uploadRobot() throws AWTException, InterruptedException {

		WebDriver driver=new ChromeDriver();
		driver.get("https://demoqa.com/upload-download");
		driver.manage().window().maximize();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");

		Thread.sleep(2000);
		WebElement upload = driver.findElement(By.id("uploadFile"));
//		jse.executeScript("arguments[0].click()", upload);
		Actions builder=new Actions(driver);
		builder.click(upload).perform();
		Thread.sleep(2000);

		//pass the path of the file in stringselection classs
		StringSelection s = new StringSelection("C:\\Users\\vijayalaxmi.rajaa\\OneDrive - HCL Technologies Ltd\\Documents\\agile.docx");
		//copy the content to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);
        //copy the content
		Robot robot = new Robot();
		//press ctrl+v for pasting	
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		//release ctrl+v
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		//press and release enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

}
