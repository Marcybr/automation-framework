package br.com.VH.framework.tests.mobile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.genium_framework.appium.support.server.AppiumServer;

import br.com.VH.framework.helper.Utils;
import br.com.VH.framework.tests.pageObjects.DeviceHome;
import br.com.VH.framework.tests.pageObjects.MapsHome;
import br.com.VH.framework.tests.pageObjects.MapsTerms;
import br.com.VH.framework.tests.pageObjects.MapsTutorial;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class AccessGoogleMapsAndroid {
	
	private MobileDriver<MobileElement> driver;
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
		
		server = Utils.initializeServer();
		driver = Utils.initializeAndroidDriver(null, null);
	}
	
	@AfterTest
	public void afterTest() throws FileNotFoundException, IOException, URISyntaxException {
		// Validate and update results, gather information, etc
		driver.closeApp();
		driver.quit();
		server.stopServer();
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
		DeviceHome nh = new DeviceHome(driver);
		MapsTutorial mtu = new MapsTutorial(driver);
		MapsTerms mt = new MapsTerms(driver);
		MapsHome mh = new MapsHome(driver);

		nh.clickOnMapsIcon();
		mt.clickOnAcceptButton();
		mtu.clickOnSkipLabel();
		mh.clickOnLocationButton();
	}

}
