package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.BasePage;
import pageobjects.HomePage;

public class VerifyLogin extends TestBase{
	BasePage basePage;
	HomePage homePage;
	@BeforeClass
	public void pageObejectsInitialization() throws Exception {

		basePage = new BasePage(driver);
		homePage = new HomePage(driver);
		
	}

	@Parameters({ "username", "password" })
	@Test(priority = 0)
	public void validUserLogin(String uname, String password) throws Exception, IOException {

		basePage.userLogin(uname, password);

		try {
			basePage.acceptNativeAlerts();
		} catch (Exception e) {
		}
		homePage.mnuNavigationMenu.click();

		Assert.assertTrue(homePage.imgUserProfile.isDisplayed(),  "user not logged-in successfully");

	}
}
