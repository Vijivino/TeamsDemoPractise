package teamE;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BarcodeRead {

	WebDriver driver;

	@Test
	public void readBarcode() throws NotFoundException, IOException {

		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();

		//get the barcode from system using the path
		driver.get("C:\\Users\\vijayalaxmi.rajaa\\OneDrive - HCL Technologies Ltd\\Documents\\barcode.gif");

		String barcodeurl = driver.findElement(By.tagName("img")).getAttribute("src");//link
		System.out.println(barcodeurl);

		URL url=new URL(barcodeurl);//covert the barcodeurl into java readable url
		BufferedImage bufferedimage=ImageIO.read(url);//read the url from image

		LuminanceSource luminanceSource=new BufferedImageLuminanceSource(bufferedimage);
		BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(luminanceSource));

		Result result =new MultiFormatReader().decode(binaryBitmap);
		System.out.println(result.getText());//to print the name of the barcode

	}

}
