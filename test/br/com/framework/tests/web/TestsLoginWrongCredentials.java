package br.com.framework.tests.web;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
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

public class TestsLoginWrongCredentials {

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
	public void afterTest(ITestResult testResult) {
		// Validate and update results, gather information, etc
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed: " + testResult.getMethod().getMethodName());
		}
		if (testResult.getStatus() == ITestResult.SUCCESS) {
			System.out.println("Successful: " + testResult.getName());
		}
		driver.close();
		driver.quit();
	}
	

	  @Test(dataProvider="LoginCredentials")
	    public void testMethod(String username,String password) throws InterruptedException, IOException{
	    {
	    	String wrongmessage = "Wrong username or password.";
	    	String actualmessage;
		// Instantiate pages and execute test
		LoginPage lp = new LoginPage(driver);
		lp.getDriver().get(url);
		lp.clickOnUsernameField();
		lp.insertValueOnUsernameField(username);
		lp.clickOnPasswordField();
		lp.insertValueOnPasswordField(password);
		lp.clickOnbtnLogin();
		actualmessage = lp.getWrongCredentialsMessage();
		Assert.assertEquals( actualmessage,  wrongmessage,  "Correct message displayed.");	
		//Assert.assertEqual(String actual,String expected, String message)
		lp.saveScreenshot(driver);
		}		
	}
	
    @DataProvider(name="LoginCredentials")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { "demouser", "abc123" },
            { "demouser_", "xyz" },
            { "demouser", "nananana" },
        };
    }	

}
