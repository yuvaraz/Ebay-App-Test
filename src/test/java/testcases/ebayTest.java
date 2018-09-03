package testcases;

import org.testng.annotations.Test;

import com.appium.base.BaseClass;
import com.appium.base.CommonMethod;

import pageobjects.LoginPage;
import pageobjects.OrderProductPage;

public class ebayTest extends BaseClass {
	
	LoginPage loginPage = new LoginPage();
	OrderProductPage orderPage = new OrderProductPage();
	/**
	 * TC-0001 Verify Login Functionality
	 * @throws Exception 
	 */
	@Test(priority=1)
	public void loginTest() throws Exception {
		try {
		loginPage.getLoginDataFromSheet();
		loginPage.userLogin();
		loginPage.clickOnLoginButton();
		}
		catch(Throwable t) {
			CommonMethod.takeScreenshot("loginTest");
			log.error(t.getLocalizedMessage());
			throw new RuntimeException(t.getLocalizedMessage());
		}
	}
	/**
	 * TC-0002 Verify order product Functionality
	 * @throws Exception
	 */
	@Test(priority=2)
	public void orderProduct() throws Exception {
		try {
		/*orderPage.searchProduct("Mobiles");
		orderPage.selectProduct();
		orderPage.clickOnBuyButton();
		orderPage.clickOnReviewButton();
		orderPage.getDataFromSheet();
		orderPage.provideContactInfo();
		orderPage.clickOnConinue();*/
		}
		catch(Throwable t) {
			CommonMethod.takeScreenshot("orderProduct");
			log.error(t.getLocalizedMessage());
			throw new RuntimeException(t.getLocalizedMessage());
		}
	} 
}
