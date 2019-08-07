package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class MapsTutorial extends TestCase {

	private WebDriver driver;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='SKIP']")
	@iOSFindBy(xpath="//android.widget.TextView[@text='SKIP']")
	private MobileElement skipLabel;
	
	public MapsTutorial(MobileDriver<MobileElement> driver) {
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
	
	public void clickOnSkipLabel() {
		try{
			skipLabel.click();
		} catch (Exception e) {
			//...
		}
	}
	
}
