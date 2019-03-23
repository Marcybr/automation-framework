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

	@AndroidFindBy(id="currentCurrencyLabel")
	@iOSXCUITFindBy(id="currentCurrencyLabel")
	private MobileElement currentCurrencyLabel;
	
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
	
}
