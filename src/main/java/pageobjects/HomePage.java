package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage{

	public HomePage(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using ="com.ebay.mobile:id/home")  
	public WebElement mnuNavigationMenu;

	@FindBy(how = How.ID, using ="com.ebay.mobile:id/menuitem_home")  
	public WebElement mnuHome;

	@FindBy(how = How.ID, using ="com.ebay.mobile:id/image_user_profile")  
	public WebElement imgUserProfile;

	@FindBy(how = How.ID, using ="com.ebay.mobile:id/menuitem_sign_out")  
	public WebElement mnuSignOut;

	@FindBy(how = How.ID, using ="com.ebay.mobile:id/search_box")  
	public WebElement txtInitialSearchBox;

	@FindBy(how = How.ID, using ="com.ebay.mobile:id/search_image_btn")  
	public WebElement imgSearchByImage;

	@FindBy(how = How.ID, using ="com.ebay.mobile:id/search_src_text")  
	public WebElement txtSearchBoxAfterSearchResultFound;

//Reusable methods
	public void userLogout() throws Exception {
		mnuNavigationMenu.click();
		imgUserProfile.click();
		mnuSignOut.click();
	}


}
