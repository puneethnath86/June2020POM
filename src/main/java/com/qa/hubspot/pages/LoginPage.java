package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage extends BasePage 
{
	private WebDriver driver;
	ElementUtils eleUtil;
	
	
//	By locators/object repo
	By emailID=By.id("username");
	By password=By.id("password");
	By loginButton=By.id("loginBtn");
	By signUpLink=By.linkText("Sign up");
	
//	Login with google
	By googleSignInBtn=By.className("google-sign-in");
	By gmailID=By.xpath("//input[@type='email']");
	By gmailNextBtn=By.xpath("//div[@id='identifierNext']");
	By gmailPwd=By.xpath("//input[@type='password']");
	By gmailPwdNext=By.xpath("//div[@id='passwordNext']");
	
	
//	Create constructor of the page
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}
	
	
//	Use page actions
	@Step("Step to check the title of login page")
	public String getLoginPageTitle() {
		return eleUtil.doGetPageTitle();
	}
	@Step("Check existance of SignUp link")
	public boolean isSignUpLinkExists() {
		return eleUtil.doIsDisplayed(signUpLink);
//		return driver.findElement(signUpLink).isDisplayed(); 
	}
	/*
	public HomePage doLogin(String un, String pwd) {
		eleUtil.doSendKeys(emailID, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
//		Adding Thread.sleep to enter the verification code manually
		try {Thread.sleep(20000);}catch(Exception e) {}
		
//		This is page chaining concept, the method is returning object of the home page class since after login we would by default land on homePage
		return new HomePage(driver); 
		*/
	
	@Step("Logging in with google login credentials and provided username is : {0}")
	public HomePage doLogin(String un, String pwd) {
		eleUtil.doClick(googleSignInBtn);
		try {Thread.sleep(3000);}catch(Exception e) {}
		eleUtil.doSendKeys(gmailID, un);
		eleUtil.doClick(gmailNextBtn);
		eleUtil.doSendKeys(gmailPwd, pwd);
		eleUtil.doClick(gmailPwdNext);

		
//		This is page chaining concept, the method is returning object of the home page class since after login we would by default land on homePage
		return new HomePage(driver); 
		
	}
}
