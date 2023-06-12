package teamD;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class CookieHandle {
	
	@Test
	public void handlingCookies() {
		
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver(ops);
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//collect all the cookies
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println("No of cookies detected "+cookies.size());
		//print all the cookies
		for(Cookie each:cookies)
		{
			System.out.println(each.getName() +" : "+ each.getValue() +" - "+each.getDomain());
		}
		
		//create new cookie
		Cookie cook=new Cookie("Vijayalaxmi","243432543656");
		//add one cookie to the cookie set
		driver.manage().addCookie(cook);
		System.out.println("***newly added*** "+cook);
		Set<Cookie> cookies1 = driver.manage().getCookies();
		System.out.println("No of cookies detected after adding new cookie "+cookies1.size());
		
		//delete the newly added cookie alone
		driver.manage().deleteCookie(cook);
		Set<Cookie> cookies3 = driver.manage().getCookies();
		System.out.println("*******Cookies list size after deleting one cookie*********** "+cookies3.size());
		
		//delete all the cookies
		driver.manage().deleteAllCookies();
		
		Set<Cookie> cookies2 = driver.manage().getCookies();
		System.out.println("List size after deleting all cookies "+cookies2.size());
		
		driver.quit();
		

	}

}
