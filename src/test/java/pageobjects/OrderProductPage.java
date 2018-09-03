package pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;

import com.appium.base.BaseClass;
import com.appium.base.CommonMethod;

/**
 * The OrderProductPage contains the user Action Methods
 * 
 */
public class OrderProductPage extends BaseClass {
	
	public String add1;
	public String add2;
	public String city_data;
	public String mobile_data;
	public String zip_data;
		
	public void searchProduct(String category) throws IOException {
		CommonMethod.click("search");
		CommonMethod.sendKeys("search_box",category );
		CommonMethod.click("search_results");
	}
	public void selectProduct() throws IOException {
		CommonMethod.click("product");
	}
	public void clickOnBuyButton() throws InterruptedException, IOException {
		CommonMethod.click("buyButton");
		Thread.sleep(5000);
		}
	public void clickOnReviewButton() throws InterruptedException, IOException {
		CommonMethod.click("review");
		Thread.sleep(5000);
	}
	public void provideContactInfo() throws IOException {
		
		CommonMethod.sendKeys("address1",add1 );
		CommonMethod.sendKeys("address1", add2);
		CommonMethod.sendKeys("city", city_data);
		CommonMethod.click("select_state");
		CommonMethod.click("state_ap");
		CommonMethod.sendKeys("zip", zip_data);
		CommonMethod.sendKeys("mobile", mobile_data);
		CommonMethod.click("checkBox");
		CommonMethod.click("proceedToPay");
	}
	/**
	 * getting data from Excel Sheet name 'ContactDetails'
	 * through ExcelReader class object
	 */
	public void getDataFromSheet() {
		
		add1 = ereader.getCellData("ContactDetails", 1, 0);
		add2 = ereader.getCellData("ContactDetails", 1, 1);
		city_data = ereader.getCellData("ContactDetails", 1, 2);
		zip_data = ereader.getCellData("ContactDetails", 1, 3);
		mobile_data = ereader.getCellData("ContactDetails", 1, 4);
	}
	public void clickOnConinue() throws IOException {
		
		CommonMethod.click("coninue");
		
	}
}
