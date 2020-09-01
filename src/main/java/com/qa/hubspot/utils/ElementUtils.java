package com.qa.hubspot.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtils
{
	
	public String isHighlight;
//	BasePage basePage;
	public WebDriver driver;
	JSUtil jsUtil;
	int timeOut=20;
//	public Properties prop;
	public ElementUtils(WebDriver driver) {
		this.driver=driver;
		jsUtil=new JSUtil(driver);
	}
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
		if(Boolean.parseBoolean(BasePage.highlightEle)) {
			jsUtil.flash(element);
		}
		return element;
	}
	
	public String waitForTitlePresent(String titleValue, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains("titleValue"));
		return driver.getTitle();
	}
	
	public String doGetPageTitle() {
		return driver.getTitle();
	}
	public void doSendKeys(By locator, String text) {
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).sendKeys(text);
	}
	
	public void doClick(By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}
	
	public String doGetText(By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator).getText();
	}
	
	public void doActionsSendKeys(By locator, String text) {
		Actions act=new Actions(driver);
		act.sendKeys(getElement(locator), text).perform();
	}
	
	public void doActionsClick(By locator) {
		Actions act=new Actions(driver);
		act.moveToElement(getElement(locator)).click().perform();	
	}
	
	public void doActionsContextClick(By locator) {
		Actions act=new Actions(driver);
		act.moveToElement(getElement(locator)).contextClick().perform();	
	}
	
	public void doActionsDblClick(By locator) {
		Actions act=new Actions(driver);
		act.moveToElement(getElement(locator)).doubleClick().perform();	
	}
	
	public boolean doIsDisplayed(By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator).isDisplayed();
	}
}
