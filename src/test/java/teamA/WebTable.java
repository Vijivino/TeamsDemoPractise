package teamA;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		//giving the path of the table we created using html tags in notepad
		driver.get("C:\\Users\\vijayalaxmi.rajaa\\OneDrive - HCL Technologies Ltd\\Documents\\CountriesTable.html");
		driver.manage().window().maximize();

		//to get the no of rows and print the rows values
		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
		int rowscount = rows.size();
		System.out.println("No of rows in this table "+rowscount);
		System.out.println("*****Table content*****");
	    for(int i=1;i<=rowscount;i++) {
			String text = driver.findElement(By.xpath("//table/tbody/tr["+i+"]")).getText();
			System.out.println(text);
		}
		System.out.println("----------");

		//to get the no of columns
		List<WebElement> columns = driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
		int colcount = columns.size();
		System.out.println("No of columns in this table "+colcount);
		
		//total no of data in table
		List<WebElement> datas = driver.findElements(By.xpath("//table/tbody/tr/td"));
		int total = datas.size();
		System.out.println("Total datas in this table "+total);
		
		//to get the last cell value
		String last = driver.findElement(By.xpath("//table/tbody/tr[5]/td[4]")).getText();
		System.out.println("last cell value "+last);
		
	}	
}
