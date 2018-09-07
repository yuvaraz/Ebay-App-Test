package utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Extent report listner
 * @author madhu.burra
 *
 */

public class ExtentReporterNGBuilder implements IReporter  {
	private ExtentReports extent; 
    
	  
   
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {  
    	
    	//"dd-MMM-yyyy__hh_mm_ss" "dd-MMM-yyyy hh_mm_ss"
    	//DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh_mm_ss");
    	//extent = new ExtentReports(outputDirectory + File.separator + "ExtentReportsTestNG_"+dateFormat.format(new Date())+".html", true);
		// extent = new ExtentReports(System.getenv("WORKING_DIRECTORY") +
		// File.separator + "ExtentReport.html", true);
		extent = new ExtentReports(outputDirectory + File.separator + "ExtentReport.html", true);
      
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
           
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
               
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        //To print the TestRunner Logs
        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
            
        }
        
        extent.flush();
        extent.close();
    }
      
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {  
            	
            	test = extent.startTest("["+((String)result.getMethod().getXmlTest().getName()+"]"+"[" + (String)result.getMethod().getMethodName()+"]"));
            	            			
            	//test = extent.startTest(result.getMethod().getMethodName());                
                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));             
               
  
                for (String group : result.getMethod().getGroups())
                		test.assignCategory(group);
               
                String message = "Test " + status.toString().toLowerCase() + "ed";
                
                if(result.getParameters()!= null && result.getParameters().length > 0 && result.getParameters().length < 2) {
                		test.log(status, "Test Data1: "+ (String)result.getParameters()[0]);
                }else if(result.getParameters()!= null && result.getParameters().length > 0  && result.getParameters().length < 4) {
                		test.log(status, "Test Data1: "+ (String)result.getParameters()[0]);
                		test.log(status, "Test Data2: "+ (String)result.getParameters()[1]);
                }
                else if(result.getParameters()!= null && result.getParameters().length > 0  && result.getParameters().length < 6) {
	            		test.log(status, "Test Data1: "+ (String)result.getParameters()[0]);
	            		test.log(status, "Test Data2: "+ (String)result.getParameters()[1]);
	            		test.log(status, "Test Data3: "+ (String)result.getParameters()[2]);
                }
                else if(result.getParameters()!= null && result.getParameters().length > 0  && result.getParameters().length < 8) {
	            		test.log(status, "Test Data1: "+ (String)result.getParameters()[0]);
	            		test.log(status, "Test Data2: "+ (String)result.getParameters()[1]);
	            		test.log(status, "Test Data3: "+ (String)result.getParameters()[2]);
	            		test.log(status, "Test Data4: "+ (String)result.getParameters()[3]);
                }
  
                if (result.getThrowable() != null){
                	
                		test.log(status, "Test " + status.toString().toLowerCase() + "ed");  
	                	if (result.getThrowable() instanceof java.lang.AssertionError) {
	                		message = result.getThrowable().getMessage();
	                	}else{
                		
	                		message=result.getMethod().getMethodName()+ ": Test case got failed due to un-expected error. Please re-run again\n"
										.concat("\n<a href='.\\index.html' target='_blank'><span class='label info'>DetailedException</span></a>");
	                	
	                		/*message=result.getMethod().getMethodName()+ ": Test case got failed due to un-expected error. Please re-run again\n"
									.concat("\n<a href='\\job\\ConedF2F-Stage2-iOS\\ws\\ConedF2F-Mobile\\target\\surefire-reports\\index.html' target='_blank'><span class='label info'>DetailedException</span></a>");*/
	                	}
	                	//Attach screenshot
	                	if(result.getParameters()!= null && result.getParameters().length > 0) {
	                		
                		test.log(status,
								test.addScreenCapture(
										".\\screenshots\\"
												+ (String) result.getMethod().getXmlTest().getName() + "_"
												+ result.getMethod().getMethodName() + "_"
												+ (String) result.getParameters()[0] + ".png"));
	            
						/*test.log(status,
								test.addScreenCapture(
										"\\job\\ConedF2F-Stage2-iOS\\ws\\ConedF2F-Mobile\\screenshots\\failed\\"
												+ (String) result.getMethod().getXmlTest().getName() + "_"
												+result.getMethod().getMethodName() + "_"
												+ (String) result.getParameters()[0] + ".png"));*/
	                	}else {

						test.log(status,
								test.addScreenCapture(
										".\\screenshots\\"
										+ (String) result.getMethod().getXmlTest().getName() + "_"
										+ result.getMethod().getMethodName() + ".png"));
	                	}
                }                
                
                test.log(status, message);               
                extent.endTest(test);
            }
        }
    }
      
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }

   
}//Close ExtentReport class
