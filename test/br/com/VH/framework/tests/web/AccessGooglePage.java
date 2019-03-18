package br.com.VH.framework.tests.web;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import br.com.VH.framework.helper.Utils;
import br.com.VH.framework.tests.web.pageObjects.GooglePage;

public class AccessGooglePage {

	private WebDriver driver;
	private String url, search;

	@BeforeClass
	public void prepareTests() throws Exception {
		// Opens browsers, etc
		driver = Utils.initializeChromeDriver();
	}

	@AfterClass
	public void finishTests() {
		// Close browsers, etc
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
		GooglePage gp = PageFactory.initElements(driver, GooglePage.class);
		gp.getDriver().get(url);
		gp.clickOnSearchField();
		gp.insertValueOnSearchField(search);
		gp.saveScreenshot(driver);
	}

}
