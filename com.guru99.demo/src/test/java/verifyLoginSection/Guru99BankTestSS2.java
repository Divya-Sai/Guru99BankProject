package verifyLoginSection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.guru99.demo.Pages.LoginPage;
import com.guru99.demo.library.ExcelAPI;
import com.guru99.demo.library.ReadConfigFile;
import com.guru99.demo.library.TakeScreenshots;

public class Guru99BankTestSS2 {

	LoginPage login;
	WebDriver driver;
	ReadConfigFile configfile;
	ExcelAPI excelreader;
	TakeScreenshots screenshots;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void getExtentReports() {
		htmlReporter  = new ExtentHtmlReporter("C:\\Users\\ssaib\\git\\guru99BankProject\\com.guru99.demo\\ExtentReports\\Guru99BankTestSS2.html");
		extent  = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
	
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
	
	@BeforeTest
	public void launchBrowser() throws IOException, InterruptedException {
		test = extent.createTest("launchBrowser");
		configfile =new ReadConfigFile();
		System.setProperty("webdriver.chrome.driver", configfile.configProp("chromepath"));
		driver = new ChromeDriver();
		driver.get(configfile.configProp("Guru99BankURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.log(Status.INFO, "launchBrowser is successfully launched");
		test.addScreenCaptureFromPath("screenshot.png");
		
		//configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Guru 99 Bank Project");
        htmlReporter.config().setReportName("Guru99BankTestSS2");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}
	
	@AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
	
	/*
	 * @Test(priority=1) public void launchBrowser() throws IOException,
	 * InterruptedException { configfile =new ReadConfigFile();
	 * System.setProperty("webdriver.chrome.driver",
	 * configfile.configProp("chromepath")); driver = new ChromeDriver();
	 * driver.get(configfile.configProp("Guru99BankURL"));
	 * driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 * 
	 * }
	 */

	//Enter Invalid UserId and Valid Password
	@Test(priority=2)
	public void enterInVaildUserDetails() throws Exception {
		test = extent.createTest("enterInVaildUserDetails");
		login = new LoginPage(driver);
		excelreader = new ExcelAPI("Guru99Bank");
		
		login.enterUserId(excelreader.getCellData("Credentials", "UserID", 3));
		login.enterPassword(excelreader.getCellData("Credentials", "Password", 3));
		//login.enterUserId("mngr29213D");
		//login.enterPassword("pyhagEh");
		Thread.sleep(5000);
		login.btnLogin.click();
		screenshots.takeSnapShot(driver,"Guru99BankTestSS2");
		test.log(Status.INFO, "enterVaildUserDetails is succefully Completed");
		/*
		 * System.out.println(driver.switchTo().alert());
		 * driver.switchTo().alert().accept();
		 */


	}

	@Test(priority=3)
	public void verifyTitle() throws Exception {
		test = extent.createTest("verifyTitle");
		System.out.println("alert is: "+driver.switchTo().alert().getText());
		Thread.sleep(5000);
		System.out.println("Title of the homepage is :"+driver.getTitle());
		String Title = driver.getTitle();
		Assert.assertEquals(Title, "Guru99 Bank Manager HomePage");
		screenshots.takeSnapShot(driver,"Guru99BankTestSS2");
		test.log(Status.INFO, "verifyTitle is succefully Completed");
	}

}
