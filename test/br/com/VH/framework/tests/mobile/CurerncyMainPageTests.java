package br.com.VH.framework.tests.mobile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.github.genium_framework.appium.support.server.AppiumServer;

import br.com.VH.framework.helper.Utils;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class CurerncyMainPageTests {
	
	private MobileDriver<MobileElement> driver;
	private AppiumServer server;
	
	@BeforeTest
	public void preRequisites() throws InterruptedException, IOException, URISyntaxException {
		// Initialize variables, etc
		server = Utils.initializeServer();
		driver = Utils.initializeiOSDriver(null, null);
	}
	
	@AfterTest
	public void afterTest() throws FileNotFoundException, IOException, URISyntaxException {
		// Validate and update results, gather information, etc
		driver.closeApp();
		driver.quit();
		server.stopServer();
		Utils.closeEmulator();
	}

}
