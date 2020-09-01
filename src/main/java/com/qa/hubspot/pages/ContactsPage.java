package com.qa.hubspot.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtils;

import io.qameta.allure.Step;

public class ContactsPage extends BasePage
{
	private WebDriver driver;
	Properties prop;
	ElementUtils eleUtil;
	
	String contactName="Brian Halligan (Sample Contact)";
	By contactsLabel=By.xpath("//i18n-string[text()='Contacts']");
	By createContactBtn=By.xpath("//span[text()='Create contact']");
	By contactNametdTags=By.xpath("//span[text()='"+contactName+"']/ancestor::td");
	By contactEmailsiblingTags=By.xpath("(//span[text()='"+contactName+"']/ancestor::td/following-sibling::td/a)[1]");
	By createContact=By.xpath("//span[text()='Create contact']");
	By createContactLabelOnPopup=By.xpath("//h3[text()='Create contact']");
	By createContactEmail=By.xpath("//input[@data-field='email']");
	By createContactFirstName=By.xpath("//input[@data-field='firstname']");
	By createContactLastName=By.xpath("//input[@data-field='lastname']");
	By createContactJobTitle=By.xpath("//input[@data-field='jobtitle']");
	By createContactPhoneNo=By.xpath("//input[@data-field='phone']");
	By createBtn=By.xpath("//button[@data-selenium-test='base-dialog-confirm-btn']//span[text()='Create contact']");
//	By createdContactLabel=By.xpath("//span[contains(@class,'CompanyContactEditableTitle')]");
	By contactsBackBtn=By.xpath("(//i18n-string[text()='Contacts'])[1]");
	
	
	
	
	public ContactsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}
	@Step("This method is to get the contact email on create Contacts page")
	public String getContactEmail() {
		return eleUtil.doGetText(contactEmailsiblingTags);
	}
	
	@Step("This method is to click create contact button on create Contacts page")
	public void clickCreateContactBtn() {
		eleUtil.doClick(createContactBtn);
	}

	@Step("This method is to check if the create Contacts page is displayed")
	public boolean isCreateContactPageDisplayed() {
		return eleUtil.doIsDisplayed(createContactLabelOnPopup);
	}
	
//	public void createNewContact() {
//		fillContactDetails();
//	}

	@Step("This method is to create new contact from create Contacts page")
	public boolean createNewContact(String email, String fn, String ln, String jobTitle){
		eleUtil.doClick(createContact);
		try {Thread.sleep(3000);}catch(Exception e) {}
		eleUtil.doSendKeys(createContactEmail, email);
		eleUtil.doSendKeys(createContactFirstName, fn);
		eleUtil.doSendKeys(createContactLastName, ln);
		try {Thread.sleep(3000);}catch(Exception e) {}
		eleUtil.doSendKeys(createContactJobTitle, jobTitle);
		try {Thread.sleep(3000);}catch(Exception e) {}
		eleUtil.doClick(createBtn);
		try {Thread.sleep(3000);}catch(Exception e) {}
		String fullName=fn+" "+ln;
		By createdContactLabel=By.xpath("//span[text()='"+fullName+"']");
		try {Thread.sleep(3000);}catch(Exception e) {}
		boolean flag=eleUtil.doIsDisplayed(createdContactLabel);
		try {Thread.sleep(3000);}catch(Exception e) {}
		eleUtil.doClick(contactsBackBtn);
		return flag;
	}
}
