package teamA;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demoqa.com/broken");
		driver.manage().window().maximize();

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("No.of links are "+links.size());

		for(int i=0;i<links.size();i++) {
			String url = links.get(i).getAttribute("href");
			verifyLinks(url);
		}

		driver.quit();

	}



	public static void verifyLinks(String linkurl) throws IOException 
	{

		try
		{

		//to check the status code we need to connect httpurlconnect
		URL url=new URL(linkurl);
		HttpURLConnection httpurlconnect=(HttpURLConnection)url.openConnection();
		httpurlconnect.setConnectTimeout(5000);
		httpurlconnect.connect();
		if(httpurlconnect.getResponseCode()>=400)//below 400 codes are valid ones
		{
			System.out.println(linkurl+ " - " +httpurlconnect.getResponseMessage()+ "is a broken link");
		}

		else
		{
			System.out.println(linkurl+ " - " +httpurlconnect.getResponseMessage());
		}

		}
		catch(Exception e) {
			
		}

	}


}
