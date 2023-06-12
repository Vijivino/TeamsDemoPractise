package demoAutomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextArea {

	WebDriver driver;

	@Test
	public void textArea() throws InterruptedException, AWTException {

		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Register.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.xpath("//a[text()='WYSIWYG']")).click();
		driver.findElement(By.xpath("//a[text()='SummerNote']")).click();

		/*		try{
			WebElement parFrame = driver.findElement(By.xpath("//iframe[@id='aswift_2']"));
			driver.switchTo().frame(parFrame);
			WebElement chiFrame = driver.findElement(By.xpath("//iframe[@id='ad_iframe']"));
			driver.switchTo().frame(chiFrame);
			driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
			System.out.println("Ad is handled");
		}
		catch (Exception e) {
			System.out.println("Ad not present");
		}
		 */		
		WebElement textarea = driver.findElement(By.xpath("//div[@class='note-editable panel-body']"));

		//change the style display from none to inline
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("document.getElementsByClassName('note-editable panel-body')[0].style.display='inline'");

		textarea.clear();
		textarea.sendKeys("Hello!"+"\n");

		//bold
		driver.findElement(By.xpath("//i[@class='note-icon-bold']")).click();
		textarea.sendKeys("Hello All Bold!"+"\n");
		boolean b = driver.findElement(By.tagName("b")).isDisplayed();
		Assert.assertTrue(b,"Bold Font not verified");
		System.out.println("Bold font is validated "+driver.findElement(By.tagName("b")).getText());

		//underlined
		driver.findElement(By.xpath("//i[@class='note-icon-underline']")).click();
		textarea.sendKeys("Hello All Underlined!"+"\n");
		boolean u = driver.findElement(By.tagName("u")).isDisplayed();
		Assert.assertTrue(u,"Underline font is not verified");
		System.out.println("Underline font is verified "+driver.findElement(By.tagName("u")).getText());


		//remove font
		driver.findElement(By.xpath("//i[@class='note-icon-underline']")).click();
		driver.findElement(By.xpath("//i[@class='note-icon-bold']")).click();

		//Background color using js
		textarea.sendKeys("In green color"+"\n");
		WebElement green = driver.findElement(By.xpath("(//div[@class='note-editable panel-body']/p)[4]"));
		js.executeScript("arguments[0].style.background='green'", green);
		//background color 
		driver.findElement(By.xpath("//button[@data-original-title='More Color']")).click();
		driver.findElement(By.xpath("//button[@data-value='#FF00FF']")).click();
		textarea.sendKeys("In pink color"+"\n");
		//validation
		String color = driver.findElement(By.xpath("//p/span")).getCssValue("background-color");
		Assert.assertTrue(color.contains("255"),"colour is not verified");
		System.out.println("Background Font color is verified as "+color);
		//remove font color
		driver.findElement(By.xpath("//button[@data-original-title='More Color']")).click();
		driver.findElement(By.xpath("//button[text()='Transparent    ']")).click();
		textarea.sendKeys("In no color"+"\n");

		//font style
		driver.findElement(By.xpath("//button[@data-original-title='Font Family']")).click();
		driver.findElement(By.xpath("//span[text()='Arial Black']")).click();
		textarea.sendKeys("Font arial black");
		//validation
		String font = driver.findElement(By.xpath("//p/font")).getAttribute("face");
		Assert.assertEquals(font, "Arial Black");
		System.out.println("Font style changed and is verified as "+font);
		Thread.sleep(3000);
		//change font again using js
		WebElement arial = driver.findElement(By.xpath("//p/font"));
		js.executeScript("arguments[0].setAttribute('face', 'Courier New')",arial);
		String font2 = driver.findElement(By.xpath("//p/font")).getAttribute("face");
		Assert.assertEquals(font2, "Courier New");
		System.out.println("Font style changed and is verified as "+font2);

		//add bulletin
		Robot robo=new Robot();
        robo.keyPress(KeyEvent.VK_CONTROL);
        robo.keyPress(KeyEvent.VK_A);
        robo.keyRelease(KeyEvent.VK_A);
        robo.keyRelease(KeyEvent.VK_CONTROL);
        driver.findElement(By.xpath("//button[@data-original-title='Unordered list (CTRL+SHIFT+NUM7)']")).click();
        //validation
        boolean d1 = driver.findElement(By.xpath("//ul")).isDisplayed();
        Assert.assertTrue(d1);

	}
	
	@Test(priority=1)
	public void ckEditor() throws AWTException, InterruptedException {
		
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/CKEditor.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//paste
		driver.findElement(By.xpath("//a[@title='Paste']")).click();
		WebElement frame = driver.findElement(By.cssSelector("iframe.cke_pasteframe"));
		driver.switchTo().frame(frame);
		driver.findElement(By.tagName("body")).sendKeys("Hi from paste"+"\n");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@title='OK']")).click();
		//validation
		WebElement fr = driver.findElement(By.xpath("//iframe[@title='Rich Text Editor, editor1']"));
		driver.switchTo().frame(fr);
		String text = driver.findElement(By.tagName("p")).getText();
		Assert.assertEquals(text, "Hi from paste");
		System.out.println("Text copied from paste " +text);
		driver.switchTo().defaultContent();
		
		//paste from clipboard
		driver.findElement(By.xpath("//a[@title='Paste from Word']")).click();
		WebElement frame1 = driver.findElement(By.cssSelector("iframe.cke_pasteframe"));
		driver.switchTo().frame(frame1);
		
		StringSelection stringselection=new StringSelection("Hi from clipboard"+"\n");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection,null);

		Robot robo = new Robot();
		robo.delay(2000);
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@title='OK']")).click();
		//validation
		WebElement fr1 = driver.findElement(By.xpath("//iframe[@title='Rich Text Editor, editor1']"));
		driver.switchTo().frame(fr1);
		Thread.sleep(3000);
		String text1 = driver.findElement(By.xpath("/html/body/p[2]")).getText();
		Assert.assertEquals(text1, "Hi from clipboard");
		System.out.println("Text copied from paste " +text1);
		driver.switchTo().defaultContent();
		
	
		
	}

}
