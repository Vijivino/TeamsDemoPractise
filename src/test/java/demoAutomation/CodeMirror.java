package demoAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CodeMirror {
	
	WebDriver driver;
	
	@Test
	public void testCodeMirror() {
		
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/CodeMirror.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//by default we should go inside the codemirror class and move the focus inside the code mirror
		WebElement codemirror = driver.findElement(By.className("cm-s-default"));//here the class name is "CodeMirror cm-s-default"
		           //so the gap should be avoided using cssselector "CodeMirror.cm-s-default" or can avoid the name before space in xpath "cm-s-default"

//		driver.findElement(By.className("codemirror-textarea")).click();
//		driver.findElement(By.className("codemirror-textarea")).sendKeys("Code is here");
//		driver.findElement(By.xpath("//div[@class='CodeMirror-lines']")).sendKeys("code is here");
		
		//get the line and click, so that cursor will be pointing in the code line
		codemirror.findElements(By.className("CodeMirror-lines")).get(0).click();
		//enter the code under the text area
		codemirror.findElement(By.tagName("textarea")).sendKeys("code"+"\n");
		codemirror.findElement(By.tagName("textarea")).sendKeys("is"+"\n");
		codemirror.findElement(By.tagName("textarea")).sendKeys("here"+"\n");
		//validation
		String text1 = codemirror.findElements(By.className("CodeMirror-line")).get(0).getText();
		Assert.assertTrue(text1.contains("code"));
		System.out.println("Code entered as "+text1);
		String text2 = codemirror.findElements(By.className("CodeMirror-line")).get(1).getText();
		Assert.assertEquals(text2, "is");
		System.out.println("Code entered as "+text2);
		String text3 = codemirror.findElements(By.className("CodeMirror-line")).get(2).getText();
		Assert.assertEquals(text3, "here");
		System.out.println("Code entered as "+text3);
			

	}

}
