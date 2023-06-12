package teamA;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://trends.builtwith.com/websitelist/Responsive-Tables");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//no.of rows
		List<WebElement> rows = driver.findElements(By.xpath("//table/thead/tr/th"));
		int rowcount = rows.size();
		System.out.println("Rows count "+rowcount);

		//to get the no of columns
		List<WebElement> columns = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		int colcount = columns.size();
		System.out.println("No of columns in this table "+colcount);
		
		System.out.println("*****Second column website values*****");
	    for(int i=1;i<=colcount;i++) {
			String text = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(text);
		}
		System.out.println("----------");
		


	}

}
