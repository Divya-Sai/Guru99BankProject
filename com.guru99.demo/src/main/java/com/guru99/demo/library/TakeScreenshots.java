package com.guru99.demo.library;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class TakeScreenshots {
	WebDriver driver;

	public void takeScreenshot(String pageName) throws IOException {

		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\ssaib\\git\\guru99BankProject\\com.guru99.demo\\src\\main\\resources\\Screenshot\\"+pageName+".jpeg");
		Files.copy(src, dest);

	}
	
	public  void takeSnapShot(WebDriver webdriver,String FileName) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination
        		File DestFile = new File("C:\\Users\\ssaib\\git\\guru99BankProject\\com.guru99.demo\\src\\main\\resources\\Screenshot\\"+FileName+".jpeg");

               // File DestFile=new File(fileWithPath);

                //Copy file at destination

                Files.copy(SrcFile, DestFile);

    }


}
