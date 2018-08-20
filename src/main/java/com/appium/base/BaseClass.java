package com.appium.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.appium.core.AppiumManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

    public class BaseClass extends GenerateLogs
    {
    	
    	public static AppiumDriver<MobileElement> driver;
    	public static ExcelReader ereader ;
    	
	 @BeforeClass
     @Parameters({"port","device"})
     public void Setup(String port,String device) throws Exception {  
		 generateLogs();
		 log.info("Configuration setup.....");
		 ereader = new ExcelReader(System.getProperty("user.dir")+"//src//main//resources//config//ebay.xlsx");
		 AppiumManager.startAppiumServer(port);
    	 DesiredCapabilities capabilities = new DesiredCapabilities();
         capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
         capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
         capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
         capabilities.setCapability("appPackage", "com.ebay.mobile");
         capabilities.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
         driver = new AppiumDriver<>(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);       
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         log.info("Application opended Successfully.....");
 
     }
     
	 	@AfterClass()
		public void tearDown() throws Exception {
			driver.quit();
		}

		
      
   
     
     
     
     
     
    }