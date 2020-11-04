package verifyLoginSection;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
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

public class Guru99BankTestSS1 {

	LoginPage login;
	WebDriver driver;
	ReadConfigFile configfile;
	ExcelAPI e;
	TakeScreenshots screenshots;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void getExtentReports() {
		htmlReporter  = new ExtentHtmlReporter("C:\\Users\\ssaib\\git\\guru99BankProject\\com.guru99.demo\\ExtentReports\\Guru99BankTestSS1.html");
		extent  = new ExtentReports();
		extent.attachReporter(htmlReporter);
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
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

	@Test(priority=2)
	public void enterVaildUserDetails() throws Exception {
		test = extent.createTest("enterVaildUserDetails");
		login = new LoginPage(driver);
		e = new ExcelAPI("Guru99Bank");
		screenshots = new TakeScreenshots();

		login.enterUserId(e.getCellData("Credentials", "UserID", 2));
		login.enterPassword(e.getCellData("Credentials", "Password", 2));
		//login.enterUserId("mngr292130");
		//login.enterPassword("pyhagEh");
		Thread.sleep(5000);
		login.btnLogin.click();
		screenshots.takeSnapShot(driver,"Guru99BankTestSS1-copy");
		test.log(Status.INFO, "enterVaildUserDetails is succefully Completed");

	}

	@Test(priority=3)
	public void verifyTitle() throws IOException {
		test = extent.createTest("verifyTitle");
		System.out.println("Title of the homepage is :"+driver.getTitle());
		String Title = driver.getTitle();
		Assert.assertEquals(Title, "Guru99 Bank Manager HomePage");
		Assert.assertEquals("true", "false");
		test.log(Status.INFO, "verifyTitle is succefully Completed");
		test.addScreenCaptureFromPath("screenshot.png");
	}

	@Test(priority=4)
	public void verifyManagerID() throws IOException {
		test = extent.createTest("verifyManagerID");
		login = new LoginPage(driver);
		login.getManagerID();
		test.log(Status.INFO, "verifyManagerID is succefully Completed");
		//test.skip("VerifyMangerID is skipped");
		test.addScreenCaptureFromPath("screenshot.png");
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
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


}
