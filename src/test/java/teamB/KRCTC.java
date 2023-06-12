package teamB;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class KRCTC {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	
		WebDriver driver=new ChromeDriver();
		driver.get("https://ksrtc.in/oprs-web/login/show.do");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
		//driver.findElement(By.cssSelector("#label[class='custom-control-label']::before")).click();
		//window.getComputedStyle(parent, ':before');
		//WebElement webElement = driver.findElement(By.id("TermsConditions"))	;	
		//String script = "return window.getComputedStyle(arguments[0],':after').getPropertyValue('content')";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("window.getComputedStyle(arguments[0],'::after').click();", webElement);
		//String content = (String) js.executeScript(script, webElement);
		
		//String script = "return window.getComputedStyle(document.querySelector('#TermsConditions'),':before')";
		//JavascriptExecutor js = (JavascriptExecutor).Driver;
		//js.executeScript("arguments[0].click(); ", script);
		
		WebElement ele = driver.findElement(By.cssSelector("label.custom-control-label"));
		//js.executeScript("document.querySelector('label.custom-control-label').click()");
		js.executeScript("document.querySelector('label.custom-control-label','::before').click()");
		
		//to get the property style attribute display
		String display = js.executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('display');",ele).toString();
        System.out.println(display);
	
	
	
	
	
	
	}
	
	

}
