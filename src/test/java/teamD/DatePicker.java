package teamD;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//get the date we want to choose
		String month="june";
		String date="20";

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.tripodeal.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.name("trip_start_date")).click();

		//check the month of the display is same as the given
		while(true) 
		{
			//get text of the month displayed
			String targetMonth = driver.findElement(By.xpath("//div[@class='picker__month']")).getText();

			//check is same as the given 
			if(targetMonth.equalsIgnoreCase(month))
			{
				break;
			}
			else
			{
				//click the next month icon
				driver.findElement(By.xpath("//*[@id=\"dateNew_root\"]/div/div/div/div/div[1]/div[4]")).click();
			}
		}
		
		//select the date given from the given month
		driver.findElement(By.xpath("//table/tbody/tr/td/div[text()='"+date+"']")).click();


	}

}
