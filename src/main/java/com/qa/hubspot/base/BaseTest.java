package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public BasePage basePage;
	public LoginPage loginPage;
	public Properties prop;
	public HomePage homePage;
	public ContactsPage contactsPage;
	
//	/*
	@Parameters({"browser"})
	@BeforeTest(enabled=true)
	public void setup(String browserName) {
		basePage=new BasePage();
		prop=basePage.init_prop();
		prop.setProperty("browser", browserName);
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);
	}
//	*/
	
	/*
	@BeforeTest
	public void setup() {
		basePage=new BasePage();
		prop=basePage.init_prop();
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);
	}
	*/
	
	@AfterTest
	public void TearDown() {
		driver.quit();
	}
}
