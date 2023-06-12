package teamD;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CalendarDatePicker {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.id("second_date_picker")).click();
		selectDate(driver, "27/Feb/2024");


	}

	public static void selectDate(WebDriver driver, String date) throws Exception {
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

		int targetMonth = calendar.get(Calendar.MONTH);System.out.println(targetMonth);
		int targetYear = calendar.get(Calendar.YEAR);
		int targetDay = calendar.get(Calendar.DAY_OF_MONTH);

		String currentDate = driver.findElement(By.className("ui-datepicker-title")).getText();
		calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
		int currentMonth = calendar.get(Calendar.MONTH);System.out.println(currentMonth);
		int currentYear = calendar.get(Calendar.YEAR);

		while(currentMonth < targetMonth || currentYear < targetYear) {
			driver.findElement(By.className("ui-datepicker-next")).click();
			currentDate = driver.findElement(By.className("ui-datepicker-title")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
			currentMonth = calendar.get(Calendar.MONTH);
			currentYear = calendar.get(Calendar.YEAR);
		}

		while(currentMonth > targetMonth || currentYear > targetYear) {
			Actions act =  new Actions(driver);
			act.moveToElement(driver.findElement(By.className("ui-datepicker-prev"))).click().perform();

			//driver.findElement(By.className("ui-datepicker-prev")).click();
			currentDate = driver.findElement(By.className("ui-datepicker-title")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
			currentMonth = calendar.get(Calendar.MONTH);
			currentYear = calendar.get(Calendar.YEAR);
		}

		if(currentMonth == targetMonth && currentYear == targetYear)
		{
			//driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()="+targetDay+"]")).click();
			driver.findElement(By.xpath("//tbody/tr/td/a[text()='"+targetDay+"']")).click();
		}

		else
			throw new Exception("unable to select the date because of current and target dates mismatch");
	}





}
