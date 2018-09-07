package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class reads the property values
 * @author madhu.burra
 *
 */
public class ReadConfigProperties {	

	Properties configProp;
	
	public ReadConfigProperties (String configFilePath) {
	     
		configProp = new Properties();
	     
	    try {
	        FileInputStream fis = new FileInputStream(configFilePath);
	        configProp.load(fis);
	        fis.close();
	    }catch (IOException e) {
	        System.out.println(e.getMessage());
	        System.out.println("property file '" + configFilePath + "' not found in the classpath\n"+e.getMessage());
	    }
	  }
	public String getConfigProperty(String propertyName){
		 String propName = configProp.getProperty(propertyName);
		 return propName;
	}

}
