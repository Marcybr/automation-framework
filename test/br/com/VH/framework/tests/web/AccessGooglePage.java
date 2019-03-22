package br.com.VH.framework.tests.web;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import br.com.VH.framework.helper.Utils;
import br.com.VH.framework.tests.pageObjects.GoogleHome;

public class AccessGooglePage {

	private WebDriver driver;
	private String url, search;

	@BeforeClass
	public void prepareTests() throws Exception {
	}

	@AfterClass
	public void finishTests() {
	}

	@BeforeTest
	public void preRequisites() throws Exception {
		// Initialize variables, etc
		url = "https://www.google.com.br";
		search = "Gabriel Aguido Fraga";
		driver = Utils.initializeChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		// Validate and update results, gather information, etc
		driver.close();
		driver.quit();
	}

	@Test
	public void accessGooglePage() throws IOException {
		// Instantiate pages and execute test
		GoogleHome gh = new GoogleHome(driver);
		gh.getDriver().get(url);
		gh.clickOnSearchField();
		gh.insertValueOnSearchField(search);
		gh.saveScreenshot(driver);
	}

}
