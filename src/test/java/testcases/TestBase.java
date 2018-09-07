package testcases;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class TestBase {

	protected static AppiumDriver<WebElement> driver;

	protected static DesiredCapabilities capabilities = null;
	protected static SoftAssert softAssert = null;

	@Parameters({ "platform" })
	@BeforeTest(alwaysRun = true)
	public void driverSetUp(@Optional("Android") String platformType) throws Exception {

		capabilities = new DesiredCapabilities();

		switch (platformType.toLowerCase()) {
		case "android":
			
			capabilities.setCapability("deviceName", " 192.168.208.101:5555"); //52008010c0bc2591
			capabilities.setCapability("appPackage", "com.ebay.mobile");
			capabilities.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
			capabilities.setCapability("appWaitActivity", "com.ebay.mobile.activities.MainActivity");
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("clearSystemFiles", true);
			capabilities.setCapability("newCommandTimeout", 90);
			capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability("resetKeyboard", true);
			//capabilities.setCapability("gpsEnabled", true);
			//capabilities.setCapability("recreateChromeDriverSessions", true);
			//capabilities.setCapability("orientation", "PORTRAIT");
			//driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver = new AndroidDriver<WebElement>(new URL("http://192.168.180.135:4723/wd/hub"), capabilities);
			
			break;

		case "ios":
			capabilities.setCapability("automationName", "XCUITest");
			capabilities.setCapability("platformVersion", "11.4.1");
			capabilities.setCapability("deviceName", "iPhone 6 Plus");
			//capabilities.setCapability("udid", "6346556");
			capabilities.setCapability("bundleId", "com.ebay.mobile");
			//capabilities.setCapability("fullReset", false);
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("clearSystemFiles", true);
			//capabilities.setCapability("useNewWDA", true);
			capabilities.setCapability("usePrebuildWDA", true);
			capabilities.setCapability("useJSONSource", true);
			capabilities.setCapability("autoAcceptAlerts", true);
			driver = new IOSDriver<WebElement>(new URL("http://192.168.180.218:4723/wd/hub"), capabilities);
			//driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			break;

		default:
			System.out.println("Driver not initialized or Conenction refused");
			break;
		}

		System.out.println("App launched..");
		Thread.sleep(5000);

	}

	@AfterMethod()
	public void captureScreenShots(ITestResult result) throws Exception {
		
		String screenShotName = null;

		try {
			if (ITestResult.FAILURE == result.getStatus()) {
				if (result.getParameters() != null && result.getParameters().length > 0) {
					screenShotName = (String) result.getMethod().getXmlTest().getName() + "_"
							+ result.getMethod().getMethodName() + "_" + (String) result.getParameters()[0];
				} else {
					screenShotName = (String) result.getMethod().getXmlTest().getName() + "_"
							+ result.getMethod().getMethodName();
				}
				takeScreenshot(screenShotName);
			}

		} catch (Exception e) {

			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}


	@AfterTest()
	public void tearDown() throws Exception {
		driver.quit();
	}


	public  boolean takeScreenshot(final String name) throws Exception {

		String destDir = "";
		destDir = "screenshots/failed";
		//destDir = "target/surefire-reports/screenshots";
		new File(destDir).mkdirs();
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("Taken Screenshot");
		return screenshot.renameTo(new File(destDir, String.format("%s.png", name)));


	}
}
