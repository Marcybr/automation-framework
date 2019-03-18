package br.com.VH.framework.tests.web.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.VH.framework.helper.TestCase;

public class GooglePage extends TestCase {

	private WebDriver driver;
	
	@FindBy(name="q")
	private WebElement seachField;
	
	public GooglePage(WebDriver driver) {
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
