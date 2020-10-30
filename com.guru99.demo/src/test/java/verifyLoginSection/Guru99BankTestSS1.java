package verifyLoginSection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.guru99.demo.Pages.LoginPage;
import com.guru99.demo.library.ReadConfigFile;

public class Guru99BankTestSS1 {

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
	@Test(priority=2)
	public void enterVaildUserDetails() throws InterruptedException {
		login = new LoginPage(driver);

		login.enterUserId("mngr292130");
		login.enterPassword("pyhagEh");
		Thread.sleep(5000);
		login.btnLogin.click();

	}
	
	@Test(priority=3)
	public void verifyTitle() {
		System.out.println("Title of the homepage is :"+driver.getTitle());
		String Title = driver.getTitle();
		Assert.assertEquals(Title, "Guru99 Bank Manager HomePage");
	}
	

}
