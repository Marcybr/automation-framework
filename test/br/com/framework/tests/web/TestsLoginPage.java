package br.com.framework.tests.web;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import br.com.framework.helper.Utils;
import br.com.framework.tests.pageObjects.InvoiceListPage;
import br.com.framework.tests.pageObjects.LoginPage;

public class TestsLoginPage {

	private WebDriver driver;
	private String url, password, username;

	@BeforeClass
	public void prepareTests() throws Exception {
	}

	@AfterClass
	public void finishTests() {
	}

	@BeforeMethod
	public void preRequisites() throws Exception {
		// Initialise variables, etc
		url = "https://automation-sandbox.herokuapp.com/";
		username ="demouser";
		password ="abc123";		
		driver = Utils.initializeChromeDriver();
	}

	@AfterMethod
	public void afterTest() {
		// Validate and update results, gather information, etc
		driver.close();
		driver.quit();
	}

	@Test
	public void accessTestPage() throws IOException {
		//Instantiate pages and execute test
		WebElement element;
		LoginPage lp = new LoginPage(driver);
		//InvoiceListPage ip = new InvoiceListPage(driver);
		lp.getDriver().get(url);
		lp.clickOnUsernameField();
		lp.insertValueOnUsernameField(username);
		lp.clickOnPasswordField();
		lp.insertValueOnPasswordField(password);
		lp.clickOnbtnLogin();
		element = InvoiceListPage.invoicePageLoaded(driver);
		Assert.assertEquals(true, element.isDisplayed());
		lp.saveScreenshot(driver);
	}	
	
    }	


