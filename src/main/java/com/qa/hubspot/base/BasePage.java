package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;



/**
 * @author punath
 *
 */
public class BasePage 
{
	
	WebDriver driver;
	Properties prop;
	OptionsManager optMan;
	public static String highlightEle;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
//	public ElementUtils eleUtil;
	/**
	 * This method is used to initialize the driver and launch respective browser
	 * @param browser
	 * @return this returns driver object
	 *
	 */
	public WebDriver init_driver(Properties property) {
		highlightEle=property.getProperty("highlight");
		String browser=property.getProperty("browser").trim();
		optMan=new OptionsManager(property);
		System.out.println("Browser name is : "+browser);
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver(optMan.getChromeOptons());
//			We are using ThreadLocal class refence below and launching the chrome driver
			tlDriver.set(new ChromeDriver(optMan.getChromeOptons()));
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver(optMan.getFirefoxOptons());
			tlDriver.set(new FirefoxDriver(optMan.getFirefoxOptons()));
		}
		
		else if(browser.equalsIgnoreCase("safari")) {
			driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("Please pass a valid browser value");
		}
	
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(property.getProperty("url").trim());
//		eleUtil=new ElementUtils(driver);
//		return driver;
		return getDriver();
	}
	
	/**
	 * getDriver using thread local concept, we have set the driver in above method now we have to get the driver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to get properties values from config file
	 * @return object of Properties object(prop)
	 */
	public Properties init_prop() {
		
		prop=new Properties();
		try 
		{
			FileInputStream fis=new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(fis);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenshot() {
//		C:\Users\punath\Documents\Eclipse Workspace\NaveenJun\JavaPrac\June2020POMSeries\Screenshots
//		./June2020POMSeries/Screenshots
		/**
		 * This method is used to take screenshots
		 * Below are the steps
		 * 1. Convert/cast driver to TakesScreenshot interface type
		 * 2. Use getScreenshotAs() and get the screenshot as a FILE
		 * 3. Store it in src that is a temp java memory and then copy this file to the "Screenshots" folder within the current project
		 * 4. To reach to current user directory/path we use system.getProperty("user.dir") and give the timestamp and file type. This would decide the file name and path
		 * 5. Now we will have to copy the captured screenshot(SRC) to mentioned location and path(DEST)
		 * 6. For copying we will use FileUtils.copyFile() method 
		 */
		
//		src is the java memory and not an actual path
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/Screenshots/"+System.currentTimeMillis()+".png";
		File desitnation = new File(path);
		
		try {
			FileUtils.copyFile(src, desitnation);
		} 
		catch (IOException e) {e.printStackTrace();}
		return path;
	}
}
