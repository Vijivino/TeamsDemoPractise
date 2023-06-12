package teamE;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChatBot {

	WebDriver driver;

	@Test
	public void interactChat() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://www.dominos.co.in/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(9000);
		driver.findElement(By.id("ymDivCircle")).click();

		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[contains(text(),'Stores near me')]")));
		driver.findElement(By.xpath("//strong[contains(text(),'Stores near me')]")).click();

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/strong[text()='Enter location']")));
//		Thread.sleep(8000);
//		driver.findElement(By.xpath("//button/strong[text()='Enter location']")).click();
//        driver.findElement(By.id("ymChatForm")).sendKeys("perungudi,chennai");

	}

	@Test
	public void bankChat() throws InterruptedException {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver(ops);
		driver.get("https://www.icicibank.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.xpath("//img[@alt='iPal Icon']")).click();

		//apply wait till the element present
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='conversation-buttons__button']//span[text()='Credit Card']")));
		driver.findElement(By.xpath("//button[@class='conversation-buttons__button']//span[text()='Credit Card']")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='conversation-choices__button' and text()='Apply for Credit Card']")));
		driver.findElement(By.xpath("//button[@class='conversation-choices__button' and text()='Apply for Credit Card']")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='conversation-choices__button' and text()='Lifetime Free Card']")));
		driver.findElement(By.xpath("//button[@class='conversation-choices__button' and text()='Lifetime Free Card']")).click();

		driver.findElement(By.xpath("//button[@title='Good answer!']")).click();		


	}

}
