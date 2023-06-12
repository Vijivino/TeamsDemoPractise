package demoAutomation;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PDFCompare {


	String url="file:\\C:\\Users\\vijayalaxmi.rajaa\\Downloads\\samplefile.pdf";

	@Test
	public void pdfCompare() throws IOException {

		String pdfContent = getPdfContent(url);
		Assert.assertTrue(pdfContent.contains("The Selenium Browser Automation Project"));

	}

	public static String getPdfContent(String url) throws IOException {

		URL pdfURL = new URL(url);
        InputStream is = pdfURL.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        PDDocument doc = PDDocument.load(bis);
        
        //to get the entire content of file
//        PDFTextStripper strip = new PDFTextStripper();
//        String stripText = strip.getText(doc);
//        System.out.println(stripText);
 
        //to get the content from page by page
        int pages=doc.getNumberOfPages();
        System.out.println("The total number of pages "+pages);
        PDFTextStripper strip=new PDFTextStripper();
        strip.setStartPage(1);
        strip.setEndPage(2);
        String stripText=strip.getText(doc);
        System.out.println(stripText);
       
        doc.close();
        return stripText;

	}

}
