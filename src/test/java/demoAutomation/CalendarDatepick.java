package demoAutomation;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarDatepick {
	
	WebDriver driver;

	@BeforeMethod
	public void launch() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Accordion.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	@Test(priority=2,description="select date from datepicker")
	public void datePicker() throws Exception {
		String date="26/jun/2021";
		Actions builder=new Actions(driver);
		WebElement widget = driver.findElement(By.xpath("//a[text()='Widgets']"));
		
		WebElement autocomplete = driver.findElement(By.xpath("//a[text()=' Datepicker ']"));
		builder.moveToElement(widget).moveToElement(autocomplete).click().build().perform();
		if(driver.findElement(By.xpath("//iframe[@id='aswift_5']")).isDisplayed()) 
		{
			//switch to the ad frame and handle two frames and then click dismiss 
			WebElement parFrame = driver.findElement(By.xpath("//iframe[@id='aswift_5']"));
			driver.switchTo().frame(parFrame);
			WebElement chiFrame = driver.findElement(By.xpath("//iframe[@id='ad_iframe']"));
			driver.switchTo().frame(chiFrame);
			driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
			//driver.switchTo().defaultContent();
			System.out.println("Unexpected ad handled");
		}else
		{
			System.out.println("unexpected ad not present");
		}

		//click the first date icon
		//driver.findElement(By.id("datepicker1")).click();
		driver.findElement(By.xpath("//img[@class='imgdp']")).click();
		//format the date using parse
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
			System.out.println(targetDateFormat);
			targetDateFormat.setLenient(false);
			Date formattedTargetDate = targetDateFormat.parse(date);
			System.out.println(formattedTargetDate);
			calendar.setTime(formattedTargetDate);
		} catch (Exception e) {
			throw new Exception("Invalid date is provided, please check the input date!!");
		} 
		//the given date convert into integers		
		int targetMonth = calendar.get(Calendar.MONTH);
		System.out.println(targetMonth);
		int targetYear = calendar.get(Calendar.YEAR);
		System.out.println(targetYear);
		int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(targetDay);

		//the title grid date into integers
		String titleMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		calendar.setTime(new SimpleDateFormat("MMM").parse(titleMonth));
		int currentMonth = calendar.get(Calendar.MONTH);
		System.out.println(titleMonth +" became " +currentMonth);
		String titleYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		calendar.setTime(new SimpleDateFormat("YYYY").parse(titleYear));
		int currentYear = calendar.get(Calendar.YEAR);
		System.out.println(titleYear +" became "+currentYear);

		while(targetMonth > currentMonth || targetYear > currentYear) {
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			String nextMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			calendar.setTime(new SimpleDateFormat("MMM").parse(nextMonth));
			currentMonth = calendar.get(Calendar.MONTH);
			String nextYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			calendar.setTime(new SimpleDateFormat("YYYY").parse(nextYear));
			currentYear = calendar.get(Calendar.YEAR);
		}

		while(targetMonth < currentMonth || targetYear < currentYear) {
			driver.findElement(By.xpath("//span[text()='Prev']")).click();
			String prevMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			calendar.setTime(new SimpleDateFormat("MMM").parse(prevMonth));
			currentMonth = calendar.get(Calendar.MONTH);
			String prevYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			calendar.setTime(new SimpleDateFormat("YYYY").parse(prevYear));
			currentYear = calendar.get(Calendar.YEAR);
		}

		if(currentMonth == targetMonth && currentYear == targetYear)
		{
			Actions act =  new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//table/tbody/tr/td/a[text()='"+targetDay+"']"))).click().perform();
			//driver.findElement(By.xpath("//table/tbody/tr/td/a[text()='"+targetDay+"']")).click();
		}

		else {
			throw new Exception("unable to select the date because of current and target dates mismatch");
		}

		//valiadtion
		String text = driver.findElement(By.id("datepicker1")).getAttribute("value");
		System.out.println(text);
		calendar.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(text));
		int displayMonth = calendar.get(Calendar.MONTH);
		int displayDate = calendar.get(Calendar.DATE);
		Assert.assertEquals(displayMonth, targetMonth);
		Assert.assertEquals(displayDate, targetDay);
		
	}

}
