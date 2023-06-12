package teamD;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CurrentDatePicker {

	WebDriver driver;

	@Test
	public void datePicker() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		driver.manage().window().maximize();
		int date=15;
		//to feeze dom,paste this - setTimeout(function(){debugger;}, 5000) 

	driver.findElement(By.id("first_date_picker")).click();
		driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"
				+ "//tr//td/a[contains(text(),'"+date+"')]")).click();
		
	}
	

	@Test
	public void currentDatePick() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


		//click departure
		driver.findElement(By.xpath("//span[text()='Departure']")).click();

		//scroll down 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");

		//to get current date 
		DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy"); //can mention the format in which we want
		Date date = new Date();
		String Todaydate = dateFormat.format(date);
		System.out.println(Todaydate);

		//split the string in year,month and day
		String splitter[] = Todaydate.split("-");
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
		
		driver.findElement(By.xpath("//p[text()='"+day+"']")).click();

	
		//driver.quit();
	}
	

} 




