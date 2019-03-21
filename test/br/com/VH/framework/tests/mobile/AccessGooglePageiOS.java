package br.com.VH.framework.tests.mobile;

import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import br.com.VH.framework.helper.Utils;
import br.com.VH.framework.tests.pageObjects.GoogleHome;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AccessGooglePageiOS {
	
	private WebDriver driver;
	private String url, search;
	
	@BeforeClass
	public void prepareTests() throws InterruptedException, IOException, URISyntaxException {
		// Opens browsers, etc
		driver = Utils.initializeiOSDriver("192.168.0.1:5555", null);
	}
	
	@AfterClass
	public void finishTests() {
		// Close browsers, etc
		driver.closeApp();
		driver.close();
		driver.quit();
	}
	
	@BeforeTest
	public void preRequisites() {
		// Initialize variables, etc
		url = "https://www.google.com.br";
		search = "Gabriel Aguido Fraga";
	}
	
	@AfterTest
	public void afterTest() {
		// Validate and update results, gather information, etc
	}
	
	@Test
	public void accessGooglePage() throws IOException {
		// Instantiate pages and execute test
		GoogleHome gp = PageFactory.initElements(driver, GoogleHome.class);
		gp.getDriver().get(url);
		gp.clickOnSearchField();
		gp.insertValueOnSearchField(search);
		gp.saveScreenshot(driver);
	}

}
