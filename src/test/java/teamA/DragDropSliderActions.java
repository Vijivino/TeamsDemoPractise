package teamA;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragDropSliderActions {

	@Test(enabled=true)
	public void dragAndDrop() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demoqa.com/droppable");
		driver.manage().window().maximize();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,150)");

		//simple tab
		//get the source and destination
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement destination = driver.findElement(By.id("droppable"));
		Actions builder=new Actions(driver);
		builder.dragAndDrop(source, destination).perform();
		//assertion
		String dropped = destination.getText();
		System.out.println(dropped);
		Assert.assertTrue(dropped.contains("Dropped"));

		//prevent propogation tab
		//driver.findElement(By.cssSelector("a#droppableExample-tabpane-preventPropogation")).click();
		driver.findElement(By.id("droppableExample-tab-preventPropogation")).click();
		WebElement source1 = driver.findElement(By.cssSelector("div#dragBox"));
		WebElement desti1 = driver.findElement(By.cssSelector("div#notGreedyInnerDropBox"));
		builder.clickAndHold(source1).moveToElement(desti1).release().build().perform();
		String dropped1 = desti1.getText();
		System.out.println(dropped1);
		Assert.assertTrue(dropped1.contains("Dropped"));


		//revert tab
		driver.findElement(By.xpath("//a[text()='Revert Draggable']")).click();
		//driver.findElement(By.id("droppableExample-tabpane-revertable")).click();
		WebElement source2 = driver.findElement(By.cssSelector("div#revertable"));
		WebElement desti2 = driver.findElement(By.id("droppable"));
		int xoffset = desti2.getLocation().x;
		int yoffset = desti2.getLocation().y;
		System.out.println(xoffset +"," +yoffset);
		//builder.dragAndDrop(source2, desti2).click().perform();
		builder.clickAndHold(source2).moveToElement(source2, 200,0).release().perform();//gave this horizontal position more than the y offset
		//.moveToElement(desti2).pause(Duration.ofSeconds(10))
		//.click(desti2).release().build().perform();
		Thread.sleep(3000);
		//String text = driver.findElement(By.xpath("//p[text()='Dropped!']")).getText();
		String text = desti1.getText();
		System.out.println(text);
		Assert.assertTrue(text.contains("Dropped"));



		//driver.quit();

	}

	@Test(priority=1)
	public void moveSlider() {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demoqa.com/slider");
		driver.manage().window().maximize();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,150)");

		WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
		Actions builder=new Actions(driver);
		//move cursor to 50
		builder.moveToElement(slider, 50, 0).click().perform();

		System.out.println("Slider moved successfully");


	}


}
