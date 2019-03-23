package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CurrencySettingsPage extends TestCase {
	
	private MobileDriver<MobileElement> driver;
	
	@AndroidFindBy(id="currentCurrencyTextField")
	@iOSXCUITFindBy(id="currentCurrencyTextField")
	private MobileElement currentCurrencyTextField;
	
	@AndroidFindBy(id="valueTextField")
	@iOSXCUITFindBy(id="valueTextField")
	private MobileElement valueTextField;
	
	@AndroidFindBy(id="backButton")
	@iOSXCUITFindBy(id="backButton")
	private MobileElement backButton;
	
	public CurrencySettingsPage(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(MobileDriver<MobileElement> driver) {
		this.driver = driver;
	}
	
	public void clickOnBackButton(){
		backButton.click();
	}
	
	public void insertValueOnValueTextField(String keys){
		valueTextField.click();
		valueTextField.sendKeys(keys);
	}
	
	public void insertValueOnCurrentCurrencyField(String keys){
		currentCurrencyTextField.click();
		currentCurrencyTextField.sendKeys(keys);
	}
	
	public String getTitle(){
		return driver.getTitle();
	}

}
