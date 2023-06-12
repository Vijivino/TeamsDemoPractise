package makeMyTrip;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookFlight {

	WebDriver driver;
	String fromdate="11/May/2023";
	String arrivaldate="29";


	@BeforeMethod
	public void launc() {

		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void bookflight() throws InterruptedException {

		Actions builder=new Actions(driver);
		builder.moveByOffset(20, 50).click().perform();
		//close the popup
		//driver.findElement(By.xpath("//span[@class='ic_circularclose_grey']")).click();
		//reound trip
		driver.findElement(By.xpath("//li[text()='Round Trip']")).click();
		//enter from and to
		driver.findElement(By.id("fromCity")).sendKeys("chennai",Keys.ENTER);
		//driver.findElement(By.xpath("//p[text()='Chennai, India']")).click();
		driver.findElement(By.id("toCity")).sendKeys("mumbai",Keys.ENTER);
		driver.findElement(By.xpath("//p[text()='Mumbai, India']")).click();
		//click departure
		driver.findElement(By.xpath("//span[text()='Departure']")).click();


		//split the string in year,month and day
		String splitter[] = fromdate.split("/");
		String year=splitter[2];
		String month = splitter[1];
		String day = splitter[0];
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);

		while(true)
		{
			String targetMonth = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
			if(targetMonth.contains(month)) 
			{
				break;
			}
			else 
			{
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
		}


		//driver.findElement(By.xpath("//p[text()='"+day+"']")).click();
		WebElement dateEle = driver.findElement(By.xpath("//p[text()='"+day+"']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", dateEle);

		//click return
		//driver.findElement(By.id("return")).click();

		//driver.findElement(By.xpath("//p[text()='"+arrivaldate+"']")).click();
		WebElement toEle = driver.findElement(By.xpath("//p[text()='"+arrivaldate+"']"));
		executor.executeScript("arguments[0].click()", toEle);

		//choose traveller
		//driver.findElement(By.id("travellers")).click();
		//driver.findElement(By.xpath("//button[text()='APPLY']")).click();
		//click search
		WebElement search = driver.findElement(By.xpath("//a[text()='Search']"));
		executor.executeScript("arguments[0].click()", search);

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='OKAY, GOT IT!']")).click();

		//select nonstop
		driver.findElement(By.xpath("//p[contains(text(),'Non Stop')]")).click();
		Thread.sleep(2000);

		//scrolldown
		executor.executeScript("window.scrollBy(0,400)");
		//slider
		WebElement slider = driver.findElement(By.xpath("//div[@class='rangeslider rangeslider-horizontal']"));
		//executor.executeScript("arguments[0].setAttribute('aria-valuenow', '9000')",slider);
		builder.clickAndHold(slider).moveByOffset(20, 30).release().build().perform();
	
		//select 3rd flight
		driver.findElement(By.id("flightCard-9")).click();
		driver.findElement(By.id("flightCard-2")).click();

		   //builder.moveToElement(slider, 180, 0).click().perform();
		  //slider.sendKeys(Keys.ARROW_RIGHT);
		 //builder.dragAndDropBy(slider, 243, 10).perform();
		//executor.executeScript("arguments[0].setAttribute('style', 'left: 30%;')",slider);

		driver.findElement(By.xpath("//button[text()='Book Now']")).click();

		driver.findElement(By.xpath("//button[text()='Continue']")).click();

		//move to next window
		Set<String> Handles = driver.getWindowHandles();
		System.out.println("No.of windows opened on first button  "+Handles.size());
		List<String> list1=new ArrayList<String>(Handles);
		driver.switchTo().window(list1.get(1));
		executor.executeScript("window.scrollBy(0,600)");

		driver.findElement(By.xpath("//button[text()='+ ADD NEW ADULT']")).click();
		driver.findElement(By.xpath("//input[@placeholder='First & Middle Name']")).sendKeys("vijayalaxmi");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Raja");
		driver.findElement(By.xpath("//input[@value='FEMALE']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Mobile No']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("viji@gmail.com");
		driver.findElement(By.xpath("//div[text()='viji@gmail.com']")).click();
		WebElement continuEle = driver.findElement(By.xpath("//button[text()='Continue']"));
		executor.executeScript("arguments[0].click()", continuEle);
		builder.moveToElement(continuEle).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='CONFIRM']")).click();
		 
	}

}
