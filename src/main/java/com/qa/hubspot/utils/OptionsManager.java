package com.qa.hubspot.utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptons() {
		co=new ChromeOptions();
		String isHeadless=prop.getProperty("headless");
		if(Boolean.parseBoolean(isHeadless)) {
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptons() {
		fo=new FirefoxOptions();
		String isHeadless=prop.getProperty("headless");
		if(Boolean.parseBoolean(isHeadless)) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
		}
		return fo;
	}
	
	
}
