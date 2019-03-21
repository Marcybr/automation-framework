package br.com.VH.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.VH.framework.helper.TestCase;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindAll;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBys;

public class GoogleHome extends TestCase {

	private WebDriver driver;
	
	@AndroidFindBy(id="search_box_text")
	@iOSXCUITFindBy(id="search_box_text")
	@FindBy(name="q")
	private WebElement seachField;
	
	public GoogleHome(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void open(String url) {
		driver.get(url);
	}
	
	public void close() {
		driver.quit();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void clickOnSearchField() {
		seachField.click();
	}
	
	public void insertValueOnSearchField(String keys){
		seachField.sendKeys(keys);
	}
	
}
