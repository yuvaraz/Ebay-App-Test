package com.appium.core;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


public class AppiumManager   {
	
	
    private final static Logger log = Logger.getLogger(AppiumManager.class);
    private static AppiumDriverLocalService service;
	private static AppiumServiceBuilder builder;
	private static DesiredCapabilities cap;
    
	public static void startAppiumServer(String port) throws Exception
	{ 
		try{
			/*
			 * Set Capabilities
			 */
			cap = new DesiredCapabilities();
			cap.setCapability("noReset", "false");
			
			/*
			 * Build the Appium service
			 */
			builder = new AppiumServiceBuilder();
			builder.withIPAddress("127.0.0.1");
			builder.usingPort(Integer.parseInt(port));
			builder.withCapabilities(cap);
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
			
			/*
			 * Start the server with the builder
			 */
			service = AppiumDriverLocalService.buildService(builder);
			service.start();			
			Thread.sleep(5000);
	    	log.info("Appium server started on port "+port);

		}
		catch (Throwable t) {
			System.out.println(t.getMessage());
			throw new Exception("Unable to start Appium server on specific port");
		}
	}
    
	/** 
	 * this method stops Appium server.Calls stopAppiumServer method to 
	 * @throws Exception Unable to stop appium server
	 */
	
	public static void stopAppium() throws Exception {
		try{
			Thread.sleep(5000);
			CommandLine command = new CommandLine("cmd");  
			command.addArgument("/c");  
			command.addArgument("Taskkill /F /IM node.exe");  

			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
			DefaultExecutor executor = new DefaultExecutor();  
			executor.setExitValue(1);  
			executor.execute(command, resultHandler); 
			log.info("Appium server Stopped successfully");
		}
		catch (Exception e) {
			log.info("Unable to stop Appium server");
			throw new Exception(e.getMessage());
		}
	}
	
}

