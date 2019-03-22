package br.com.VH.framework.tests.pageObjects;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DeviceHome extends TestCase {

	private MobileDriver<MobileElement> driver;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Chrome']")
	@iOSXCUITFindBy(xpath="//*[contains(@text, 'Chrome')]")
	private MobileElement chromeAppIcon;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Maps']")
	@iOSXCUITFindBy(xpath="//*[contains(@text, 'Maps')]")
	private MobileElement mapsIcon;
	
	public DeviceHome(MobileDriver<MobileElement> driver) {
		driver.closeApp();
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
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
	
    public void horizontalSwipeByPercentage (double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * endPercentage);
 
        new TouchAction(driver)
                .press(point(startPoint, anchor))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endPoint, anchor))
                .release().perform();
    }
	
}
