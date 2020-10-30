package verifyLoginSection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.guru99.demo.Pages.LoginPage;
import com.guru99.demo.library.ReadConfigFile;

public class Guru99BankTestSS2 {

	LoginPage login;
	WebDriver driver;
	ReadConfigFile configfile;

	@Test(priority=1)
	public void launchBrowser() throws IOException, InterruptedException {
		configfile =new ReadConfigFile();
		System.setProperty("webdriver.chrome.driver", configfile.configProp("chromepath"));
		driver = new ChromeDriver();
		driver.get(configfile.configProp("Guru99BankURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	//Enter Invalid UserId and Valid Password
	@Test(priority=2)
	public void enterInVaildUserDetails() throws InterruptedException {
		login = new LoginPage(driver);

		login.enterUserId("mngr29213D");
		login.enterPassword("pyhagEh");
		Thread.sleep(5000);
		login.btnLogin.click();
		/*
		 * System.out.println(driver.switchTo().alert());
		 * driver.switchTo().alert().accept();
		 */


	}

	@Test(priority=3)
	public void verifyTitle() throws InterruptedException {
		System.out.println("alert is: "+driver.switchTo().alert().getText());
		Thread.sleep(5000);
		System.out.println("Title of the homepage is :"+driver.getTitle());
		String Title = driver.getTitle();
		Assert.assertEquals(Title, "Guru99 Bank Manager HomePage");
	}

}
