package pageobjects;

import java.io.IOException;
import com.appium.base.BaseClass;
import com.appium.base.CommonMethod;

public class LoginPage extends BaseClass{


	public String email;
	public String password;
	
	public void userLogin() throws Exception {

		CommonMethod.click("singIn");
		CommonMethod.sendKeys("txtUserName", email);
		CommonMethod.sendKeys("txtPassword", password);
	}
	
	public void clickOnSignInButton() throws IOException {
		CommonMethod.click("singIn");
		
	}
	
	public void clickOnLoginButton() throws InterruptedException, IOException {
		CommonMethod.click("btnLogin");
		Thread.sleep(15000);
	}
	
	public void getLoginDataFromSheet() {
		email = ereader.getCellData("Login", 1, 0);
		password = ereader.getCellData("Login", 1, 1);
	}
	
	
	
	
	
	
	
	
	
	
	
}