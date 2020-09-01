package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.Constants;


public class HomePageTest extends BaseTest
{
	
	/**
	 * Before class will invoke the login method and then rest of the actions on home page will be performed
	 * We will use PAGE CHAINING so that we do not have to create an object of HomePage here, it would be returned as part of LoginPage itself
	 * Before we work with home page, we will have to login first, below method is to login to hubspot website and this would return a HOMEPAGE class object
	 * Below statement is same as "homePage=new HomePage()"
	 */
	@BeforeClass
	public void homePageSetup() {
		homePage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test(priority=2)
	public void validateHomePageTitleTest() {
		String pageTitle=homePage.getHomePageTitle();
		Assert.assertEquals(pageTitle, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=4)
	public void validateAccountNameDisplayedTest() {
		String accountName=homePage.checkAccountNameDisplayed();
		Assert.assertEquals(accountName, prop.getProperty("expectedAccountName").trim());
	}
	
	@Test(priority=1)
	public void validateSettingsIconDisplayed() {
		Assert.assertTrue(homePage.checkSettingIconDisplayed());
	}
	
	@Test(priority=3)
	public void validateContactsLinkDisplayed() {
		Assert.assertTrue(homePage.checkContactsLinkDisplayed());
	}
	
	@Test(priority=4)
	public void clickContacts() {
		homePage.clickContacts();
//		Assert.assertTrue(homePage.clickContacts());
		
	}
	
	@Override
	@AfterTest
	public void TearDown() {
		driver.quit();
	}
	
	
}
