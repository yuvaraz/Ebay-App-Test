package com.appium.base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * CommonMethod class contains all the 
 * Re-usable methods which are used throughout the 
 * automation of TestScripts
 */
public class CommonMethod extends BaseClass  {

    static WebDriverWait wait;
    
    public static WebElement findElement(String key) throws IOException {
    	/**
    	 * creating an object of properties class
    	 * and getting the values from properties file through the object reference
    	 */
    	Properties prop = new Properties();
    	FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config\\locator.properties");
    	prop.load(file);
    	
    	String objectKeyAndValue = prop.getProperty(key);
    	String[] value = objectKeyAndValue.split("~");
    	
    	String locatorType = value[0];
    	String locatorValue = value[1];
    	
    	if(locatorType.equalsIgnoreCase("id")) {
    		
    		return driver.findElement(By.id(locatorValue));
    	}
    	else if(locatorType.equalsIgnoreCase("xpath")) {
    		
    		return driver.findElement(By.xpath(locatorValue));
    	}
    	
    	else if(locatorType.equalsIgnoreCase("name")) {
    		
    		return driver.findElement(By.name(locatorValue));
    	}
    	else {
    		
    		throw new RuntimeException("incorrect locator type provided");
    	}
    }
    public static boolean isElementPresent(String key) throws IOException {
   	try {   
			if (findElement(key).isDisplayed()){
		     	return true;
			}else{
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
	}
    public static void click(String key) throws IOException{
    	
    	if(isElementPresent(key)) {
    		findElement(key).click();
        	log.info("Clicking on button ");
    	}else {
    		log.error("Error found in while clicking on button ");
    	}
    }
    public static void sendKeys(String key,String data) throws IOException{
    	if(isElementPresent(key)) {
        	((MobileElement) findElement(key)).setValue(data);
        	log.info("passing data to input field ");
    	}else {
    		log.error("Error found while passing data to input  field ");
    	}
    }
    public String getText(By locator){
       return driver.findElement(locator).getText();	
    }
    public int getSizeElements(By locator){
    	/*
    	 * waitForVisibilityOf(locator);
    	 */
    	List<MobileElement> elements = driver.findElements(locator);
    	return elements.size();
    }
    public void scrollPageUp() {
        System.out.println("Scrolling the content..");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.50);
        swipeObject.put("startY", 0.95);
        swipeObject.put("endX", 0.50);
        swipeObject.put("endY", 0.01);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeLeftToRight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.01);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.9);
        swipeObject.put("endY", 0.6);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.5);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeFirstCarouselFromRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.2);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.2);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void performTapAction(WebElement elementToTap) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("x", (double) 360); // in pixels from left
        tapObject.put("y", (double) 170); // in pixels from top
        tapObject.put("element", Double.valueOf(((RemoteWebElement) elementToTap).getId()));
        js.executeScript("mobile: tap", tapObject);
    }
 
    //Generic Methods
    public static WebElement scrollToText(String resourceID , String text){
    
        //make sure u give the resouce ID of the complete list of elements here as parameter

        WebElement el=driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
                + ".resourceId(\""+resourceID+"\")).scrollIntoView("
                + "new UiSelector().text(\""+text+"\"));"));
        return el;
    }
    
    public  static boolean takeScreenshot(final String name) throws Exception {

		String destDir = "";
		destDir = "screenshots/failed";
		new File(destDir).mkdirs();
		//switchToNativeView();
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("Taken Screenshot");
		return screenshot.renameTo(new File(destDir, String.format("%s.png", name)));
	}
	
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
       
}