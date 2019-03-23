package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CurrencyMainPage extends TestCase {
	
	private MobileDriver<MobileElement> driver;

	@AndroidFindBy(id="settingsCurrency")
	@iOSXCUITFindBy(id="settingsCurrency")
	private MobileElement settingsCurrencyButton;
	
	@AndroidFindBy(id="currentCurrency")
	@iOSXCUITFindBy(id="currentCurrency")
	private MobileElement currentCurrencyTitle;
	
	@AndroidFindBy(id="currencyLabel")
	@iOSXCUITFindBy(id="currencyLabel")
	private MobileElement currencyLabel;
	
	@AndroidFindBy(id="relatedCurrencyLabel")
	@iOSXCUITFindBy(id="relatedCurrencyLabel")
	private MobileElement relatedCurrencyLabel;
	
	public CurrencyMainPage(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(MobileDriver<MobileElement> driver) {
		this.driver = driver;
	}
	
	public void clickOnSettingsButton(){
		settingsCurrencyButton.click();
	}
	
	public void clickOnBRLCurrencyLabel(){
		relatedCurrencyLabel.click();
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
}
