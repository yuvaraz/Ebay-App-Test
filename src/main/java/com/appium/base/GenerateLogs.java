package com.appium.base;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class GenerateLogs {
	
	public static Logger log;

	public void generateLogs() throws IOException {
		//console   //file
		log = Logger.getLogger(getClass());
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config\\log4j.properties");
		prop.load(file);
		PropertyConfigurator.configure(prop);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
