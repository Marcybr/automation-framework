package br.com.framework.tests.web;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

import br.com.framework.helper.ReadExcelFile;
import br.com.framework.helper.Utils;
import br.com.framework.tests.pageObjects.InvoiceDetailsPage;
import br.com.framework.tests.pageObjects.InvoiceListPage;
import br.com.framework.tests.pageObjects.LoginPage;

public class TestInvoiceTable {

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
	public void accessTestPage() throws IOException, EncryptedDocumentException, InvalidFormatException {
		//Instantiate pages and execute test
		WebElement element;
		InvoiceListPage ip = new InvoiceListPage(driver);
		InvoiceDetailsPage idp = new InvoiceDetailsPage(driver);
		ReadExcelFile objExcelFile = new ReadExcelFile();
		String cellValue = ReadExcelFile.readdatafromExcelusingcolumnName("HotelName");
		System.out.println("user Name value is : "+cellValue);
		LoginPage lp = new LoginPage(driver);
		lp.getDriver().get(url);
	    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		lp.clickOnUsernameField();
		lp.insertValueOnUsernameField(username);
		lp.clickOnPasswordField();
		lp.insertValueOnPasswordField(password);
		lp.clickOnbtnLogin();
		element = InvoiceListPage.invoicePageLoaded(driver);
		Assert.assertEquals(true, element.isDisplayed());
		driver.findElement(By.cssSelector("a[href*='invoice/0']")).click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		//System.out.println("Clicou no invoice");
		element = InvoiceDetailsPage.invoiceDetailPageLoaded(driver);
		Assert.assertEquals(true, element.isDisplayed());
		//String hotelName = driver.findElement(By.className("mt-5")).getText();
		String hotelName = idp.getHotelName();
		System.out.println("capturado pelo get"+hotelName);
	
		
		lp.saveScreenshot(driver);
	}	
	
    }	


