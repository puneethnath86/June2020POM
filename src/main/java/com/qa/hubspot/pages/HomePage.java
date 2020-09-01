package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtils;

import io.qameta.allure.Step;

public class HomePage extends BasePage
{
	private WebDriver driver;
	ElementUtils eleUtil;
	
//	BY Locators for the home page class
	By header=By.tagName("h1");
	By accountName=By.xpath("//span[@class='account-name ']");
	By doTaskLabel=By.xpath("//i18n-string[contains(text(),'Do these tasks to get started')]");
	By settingsIcon=By.id("navSetting");
	By contactsLink=By.xpath("//a[@id='nav-primary-contacts-branch' and @data-tracking='click hover']");
	By contactsSubMenu=By.xpath("//div[@style='min-height: initial']//a[@id='nav-secondary-contacts']");
	By contactsLabel=By.xpath("//i18n-string[text()='Contacts']");
	
//	PageActions for the home page class
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}
	@Step("This method is to get the homepage title")
	public String getHomePageTitle() {
		try {Thread.sleep(3000);}catch(Exception e) {}
		return eleUtil.doGetPageTitle();
	}
	@Step("This method is to get the header value of homePage")
	public String getHeaderValue() {
		if(eleUtil.doIsDisplayed(header)) {
			return eleUtil.doGetText(header);
		}
		return null;
	}
	@Step("This method is to check the account name of the logged in user")
	public String checkAccountNameDisplayed() {
		if(eleUtil.doIsDisplayed(accountName)) {
			return eleUtil.doGetText(accountName);
		}
		return null;
	}
	@Step("This method is to validate the DoTask label displayed on home page")
	public boolean checkDoTaskDisplayed() {
		return eleUtil.doIsDisplayed(doTaskLabel);
	}
	@Step("This method is to validate if SETTINGS button is displayed on home page")
	public boolean checkSettingIconDisplayed() {
		return eleUtil.doIsDisplayed(settingsIcon);
	}
	@Step("This method is to validate if CONTACTS link is displayed on home page")
	public boolean checkContactsLinkDisplayed() {
		return eleUtil.doIsDisplayed(contactsLink);
	}
	@Step("This method is to click on CONTACTS Link on home page")
	public ContactsPage clickContacts() {
		eleUtil.doClick(contactsLink);
		eleUtil.doActionsClick(contactsSubMenu);
//		return eleUtil.doIsDisplayed(contactsLabel);
		return new ContactsPage(driver);
	}
	
}
