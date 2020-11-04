package com.guru99.demo.library;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportsUtil {

	ExtentHtmlReporter htmlReporter;
	com.aventstack.extentreports.ExtentReports extent;
	ExtentTest test;
	
	public void getExtentReports(String ReportName) {
		htmlReporter  = new ExtentHtmlReporter("C:\\Users\\ssaib\\git\\guru99BankProject\\com.guru99.demo\\ExtentReports\\"+ReportName+".html");
		extent = new com.aventstack.extentreports.ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	
}
