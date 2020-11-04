package com.guru99.demo.Pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;
	//WebElements
	@FindBy(xpath=".//input[@name='uid']")
	public WebElement txtUserID;

	@FindBy(xpath=".//input[@name='password']")
	public WebElement txtPassword;

	@FindBy(xpath=".//input[@name='btnLogin']")
	public WebElement btnLogin;
	
	@FindBy(xpath="//td[contains(text(),'Manger Id :')]")
	public WebElement ManagerID;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserId(String UserId) {
		txtUserID.sendKeys(UserId);
	}

	public void enterPassword(String Pass) {
		txtPassword.sendKeys(Pass);
	}

	public void clickOnLogin() {
		btnLogin.click();
	}

	public void getManagerID() {
		System.out.println(ManagerID.getText());
	}

}
