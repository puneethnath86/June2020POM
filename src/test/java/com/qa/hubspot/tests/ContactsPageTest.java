package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtils;

public class ContactsPageTest extends BaseTest{
	
	@BeforeClass
	public void contactsPageSetup() {
		homePage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		contactsPage=homePage.clickContacts();
	}
	
	@Test
	public void getContactEmailTest() {
		String email=contactsPage.getContactEmail();
		Assert.assertEquals(email, Constants.CONTACT_EMAIL_BRIAN);
	}
	
	@DataProvider()
	public Object[][] getContactsTestData() {
		ExcelUtils exUtil=new ExcelUtils();
		Object[][] dataObj=exUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return dataObj;
	}
	@Test(dataProvider="getContactsTestData")
	public void createNewContact(String email, String fn, String ln, String jobTitle) {
		Assert.assertTrue(contactsPage.createNewContact(email, fn, ln, jobTitle));
	}
}
