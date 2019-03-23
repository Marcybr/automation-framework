package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CurrencyConvertionPage extends TestCase {
	
	private MobileDriver<MobileElement> driver;
	
	@AndroidFindBy(id="currentCurrencyConvertionLabel")
	@iOSXCUITFindBy(id="currentCurrencyConvertionLabel")
	private MobileElement currentCurrencyConvertionLabel;
	
	@AndroidFindBy(id="valueTableView")
	@iOSXCUITFindBy(id="valueTableView")
	private MobileElement valueTableView;
	
	public CurrencyConvertionPage(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(MobileDriver<MobileElement> driver) {
		this.driver = driver;
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
}
