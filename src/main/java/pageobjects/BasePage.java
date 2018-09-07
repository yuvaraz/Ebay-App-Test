package pageobjects;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {
	
	protected static WebDriverWait wait = null;
	protected static  TouchAction<?> touchAction = null;
	protected static Alert alert = null;

	protected AppiumDriver<WebElement> driver;
	public BasePage(AppiumDriver<WebElement>  driver) {
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}


	@FindBy(how = How.ID, using = "com.ebay.mobile:id/button_sign_in")
	public WebElement btnSignIn;
	
	@FindBy(how = How.ID, using = "com.ebay.mobile:id/edit_text_username")
	public WebElement txtUserName;
	
	@FindBy(how = How.ID, using = "com.ebay.mobile:id/edit_text_password")
	public WebElement txtPassword;
	
	@FindBy(how = How.ID, using = "com.ebay.mobile:id/button_sign_in")
	public WebElement btnLoginIn;
	
	
	public void userLogin(String email, String password) throws Exception {

		boolean isLoginScreenDisplayed = false;
		try {
			isLoginScreenDisplayed = btnSignIn.isDisplayed();
		} catch (Exception e) {
			isLoginScreenDisplayed = false;
		}
		if (isLoginScreenDisplayed) {
			txtUserName.clear();
			txtUserName.sendKeys(email);
			System.out.println("Entered username:" + email);
			txtPassword.clear();
			txtPassword.sendKeys(password);
			System.out.println("Entered password:" + password);
			btnLoginIn.click();
			Thread.sleep(5000);

		}

	}
	
	public void acceptNativeAlerts() throws InterruptedException {
		try {
			alert = driver.switchTo().alert();
			System.out.println("Native alert handled");
			alert.accept();
			Thread.sleep(2000);

		} catch (NoSuchElementException | TimeoutException e) {
		}
	}

	public void waitForVisibilityOfElement(WebElement element) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(TimeoutException.class)
				.ignoring(NoSuchElementException.class)
				.ignoring(IndexOutOfBoundsException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(StaleElementReferenceException.class);
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver arg0) {
				//System.out.println("Waiting for element to be visible");
				if (element.isDisplayed()) {
					System.out.println("Expected Element is found.");
					return element;
				}
				return null;
			}
		};
		wait.until(function);


	}
	public void waitForVisibilityOfListElements(List<WebElement> elements) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(TimeoutException.class)
				.ignoring(NoSuchElementException.class)
				.ignoring(IndexOutOfBoundsException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(StaleElementReferenceException.class);

		Function < WebDriver, Boolean > function = new Function < WebDriver, Boolean > () {
			public Boolean apply(WebDriver arg0) {
				//System.out.println("Waiting for list elements to be visible");
				if(elements!=null) {
					elements.get(0).isDisplayed();
					System.out.println("Expected Elements list found.");
					return true;
				}
				return false;
			}

		};
		wait.until(function);
	}
	
}
