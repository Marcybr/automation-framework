package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NexusHome extends TestCase {

	private MobileDriver<MobileElement> driver;
	
	@FindBy(name="Chrome")
	@iOSXCUITFindBy(xpath="Chrome")
	private MobileElement chromeAppIcon;
	
	@AndroidFindBy(xpath="Maps")
	@iOSXCUITFindBy(xpath="Maps")
	private MobileElement mapsIcon;
	
	public NexusHome() {
	}
	
	public NexusHome(MobileDriver<MobileElement> driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(MobileDriver<MobileElement> driver) {
		this.driver = driver;
	}

	public void close() {
		driver.quit();
	}
	
	public void clickOnChromeIcon() {
		chromeAppIcon.click();
	}
	
	public void clickOnMapsIcon() {
		mapsIcon.click();
	}
	
}
