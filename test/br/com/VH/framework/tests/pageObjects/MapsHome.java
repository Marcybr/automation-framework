package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MapsHome extends TestCase {

	private WebDriver driver;
	
	@AndroidFindBy(id="mylocation_button")
	@iOSXCUITFindBy(id="mylocation_button")
	private WebElement locationButton;
	
	public MapsHome(WebDriver driver) {
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
	
	public void clickOnLocationButton() {
		locationButton.click();
	}
	
}
