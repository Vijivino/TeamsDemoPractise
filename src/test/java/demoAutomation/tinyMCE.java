package demoAutomation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tinyMCE {
	
	WebDriver driver;
	Actions a;
	JavascriptExecutor js;
	
	@BeforeMethod
	public void launch() throws InterruptedException
	{
		driver= new ChromeDriver();
		js= (JavascriptExecutor) driver;
	    a=new Actions(driver);
		driver.get("https://www.selenium.dev/selenium/web/tinymce.html");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	
	@Test(enabled=true)
	public void tiny_setContent() throws InterruptedException, IOException
	{

		//sending the input using this native api method of tinyMCE
            //no need to switch iframe in this
		js.executeScript("tinyMCE.activeEditor.setContent('<h1>Hi from setcontent</h1> TinyMCE');","");
		Thread.sleep(2000);
		js.executeScript("tinyMCE.activeEditor.insertContent('<p><strong> Hi from insertcontent</strong></p>');","");
	
		//get the text from the textarea
		String str=	(String) js.executeScript("return tinyMCE.activeEditor.getBody().textContent;", "");
		System.out.println(str);
		
		//paste the contents from notepad using send keys
		WebElement frame=driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
		driver.switchTo().frame(frame);
/*		//open the notepad
		driver.navigate().to("C:\\Users\\vijayalaxmi.rajaa\\Downloads\\mock.txt");
		String content = driver.getPageSource();
		Thread.sleep(3000);
		driver.navigate().back();
*/		
		//adding content from notepad
		String str1 = readnotepad(); //adding content line by line
		driver.findElement(By.cssSelector("body#tinymce>p")).sendKeys("\n"+str1+"\n");

		
	}
	
	public String readnotepad() throws IOException
	{
		File file = new File("C:\\Users\\vijayalaxmi.rajaa\\Downloads\\mock.txt");
		FileReader fr= new FileReader(file);
		BufferedReader br= new BufferedReader(fr);
		String line;
		while((line=br.readLine())!=null)
		{
			return line;
		}
		return line;
	
	}
}
