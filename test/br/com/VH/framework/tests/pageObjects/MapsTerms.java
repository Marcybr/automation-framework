package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MapsTerms extends TestCase {

	private WebDriver driver;
	
	@AndroidFindBy(tagName="ACCEPT & CONTINUE")
	@iOSXCUITFindBy(tagName="ACCEPT & CONTINUE")
	private WebElement acceptButton;
	
	@AndroidFindBy(tagName="CLOSE")
	@iOSXCUITFindBy(tagName="CLOSE")
	private WebElement closeButton;
	
	public MapsTerms(WebDriver driver) {
		this.driver = driver;
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
