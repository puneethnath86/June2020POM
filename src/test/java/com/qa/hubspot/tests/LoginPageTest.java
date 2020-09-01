package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest
{
	
	@Description("This is a test to validate login page title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void verifyLoginPageTitleTest()
	{
		String loginPageTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("This is a test to validate login feature")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void verifySignUpLinkTest() {
		try {Thread.sleep(3000);} catch(Exception e) {}
		Assert.assertTrue(loginPage.isSignUpLinkExists());
	}
	
	@Description("This is a test to enter username and password for login feature")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void logintest() {
		homePage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	
	
	
	
}
