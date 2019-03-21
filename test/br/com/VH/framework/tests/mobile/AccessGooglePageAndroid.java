package br.com.VH.framework.tests.mobile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.genium_framework.appium.support.server.AppiumServer;

import br.com.VH.framework.helper.Utils;
import br.com.VH.framework.tests.pageObjects.NexusHome;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AccessGooglePageAndroid {
	
	private AndroidDriver<AndroidElement> driver;
	private String url, search;
	private AppiumServer server;
	
	@BeforeClass
	public void prepareTests() {
		// Opens browsers, etc
	}
	
	@AfterClass
	public void finishTests() {
		// Close browsers, etc
	}
	
	@BeforeTest
	public void preRequisites() throws InterruptedException, IOException, URISyntaxException {
		// Initialize variables, etc
		url = "https://www.google.com.br";
		search = "Gabriel Aguido Fraga";
		
		//server = Utils.initializeServer();
		driver = Utils.initializeAndroidDriver("192.168.0.1:5555", null);
	}
	
	@AfterTest
	public void afterTest() throws FileNotFoundException, IOException, URISyntaxException {
		// Validate and update results, gather information, etc
		driver.closeApp();
		driver.quit();
		//server.stopServer();
		Utils.closeEmulator();
	}
	
	@BeforeMethod
	public void setUpTest() {
	}
	
	@AfterMethod
	public void tearDown(){
	}
	
	@Test
	public void accessGooglePage() throws IOException {
		// Instantiate pages and execute test
		NexusHome nh = PageFactory.initElements(driver, NexusHome.class);
//		MapsHome mh = PageFactory.initElements(driver, MapsHome.class);
//		MapsTerms mt = PageFactory.initElements(driver, MapsTerms.class);

		nh.clickOnMapsIcon();
//		nh.saveScreenshot(driver);
//		mt.clickOnAcceptButton();
//		mh.clickOnLocationButton();
//		nh.saveScreenshot(driver);
	}

}
