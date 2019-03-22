package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class MapsTerms extends TestCase {

	private WebDriver driver;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ACCEPT & CONTINUE']")
	@iOSFindBy(xpath="//android.widget.TextView[@text='ACCEPT & CONTINUE']")
	private WebElement acceptButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLOSE']")
	@iOSFindBy(xpath="//android.widget.TextView[@text='CLOSE']")
	private WebElement closeButton;

	public MapsTerms(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void close() {
		driver.quit();
	}
	
	public void clickOnAcceptButton() {
		try{
			acceptButton.click();			
		} catch (Exception e) {
			//...
		}
	}
	
	public void clickOnCloseButton() {
		closeButton.click();
	}
	
}
