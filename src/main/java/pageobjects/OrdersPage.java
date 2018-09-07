package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.AppiumDriver;

public class OrdersPage extends BasePage {


		
		public OrdersPage(AppiumDriver<WebElement> driver) {
			super(driver);
		}
		
		@FindBy(how = How.ID, using ="com.ebay.mobile:id/home")  
		public WebElement mnuNavigationMenu;
}
