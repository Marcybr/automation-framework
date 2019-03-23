package br.com.VH.framework.tests.mobile;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.genium_framework.appium.support.server.AppiumServer;

import br.com.VH.framework.helper.Utils;
import br.com.VH.framework.tests.pageObjects.CurrencyConvertionPage;
import br.com.VH.framework.tests.pageObjects.CurrencyMainPage;
import br.com.VH.framework.tests.pageObjects.CurrencySettingsPage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class CurrencyMainPageTests {

	private MobileDriver<MobileElement> driver;
	private AppiumServer server;
	
	@BeforeTest
	public void preRequisites() throws InterruptedException, IOException, URISyntaxException {
		// Initialize variables, etc
		server = Utils.initializeServer();
		driver = Utils.initializeiOSDriver(null, "/Desktop/CurrencyConvert.ipa");
	}
	
	@AfterTest
	public void afterTest() throws FileNotFoundException, IOException, URISyntaxException {
		// Validate and update results, gather information, etc
		driver.closeApp();
		driver.quit();
		server.stopServer();
		Utils.closeEmulator();
	}
	
	@Test
	public void accessSettingPage(){
		CurrencyMainPage cmp = new CurrencyMainPage(driver);
		cmp.clickOnSettingsButton();
		
		CurrencySettingsPage csp = new CurrencySettingsPage(driver);
		assertEquals(csp.getTitle(), "Settings");	
	}
	
	@Test
	public void accessConvertionPage(){
		CurrencyMainPage cmp = new CurrencyMainPage(driver);
		cmp.clickOnBRLCurrencyLabel();
		
		CurrencyConvertionPage ccp = new CurrencyConvertionPage(driver);
		assertEquals(ccp.getTitle(), "CAD -> BRL");	
	}
	
	@Test
	public void accessMainPage(){
		CurrencyMainPage cmp = new CurrencyMainPage(driver);
		assertEquals(cmp.getTitle(), "CAD (1)");	
	}
	
}
