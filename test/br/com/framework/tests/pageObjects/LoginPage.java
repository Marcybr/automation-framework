package br.com.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
//import io.appium.java_client.pagefactory.AndroidFindBy;
//import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends TestCase {

	private WebDriver driver;
	

	@FindBy(name="username")
	private WebElement usernameField;
	
	@FindBy(name="password")
	private WebElement passwordField;
	
	@FindBy(id="btnLogin")
	private WebElement btnLogin;
	
	@FindBy(xpath="/html/body/div/div[1]")
	private WebElement wrongCredentials;
	
	
	public LoginPage(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, LoginPage.class);
	}
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
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
	
	public void clickOnUsernameField() {
		usernameField.click();
	}
	
	public void insertValueOnUsernameField(String keys){
		usernameField.sendKeys(keys);
	}
	
	public void clickOnPasswordField() {
		passwordField.click();
	}
	
	public void insertValueOnPasswordField(String keys){
		passwordField.sendKeys(keys);
	}
	
	public void clickOnbtnLogin() {
		btnLogin.click();
	}
	public String getWrongCredentialsMessage() {
		return wrongCredentials.getText();
	}
	
	
}
