package br.com.framework.tests.pageObjects;

import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.framework.helper.TestCase;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class InvoiceListPage extends TestCase {

	private WebDriver driver;
	
	public static WebElement element = null;

	
	@FindBy(xpath="/html/body/section/div/div[1]/div[1]")		
	private WebElement invoiceNumber;
	
	@FindBy(xpath="/html/body/section/div/div[1]/div[2]")		
	private WebElement hotelName;
	
	@FindBy(xpath="/html/body/section/div/div[2]/div[1]")		
	private WebElement row1col1;
	
	public static WebElement invoicePageLoaded(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/section/div/div[1]/div[1]"));
		System.out.println("Invoice Page loaded");
		return element;
	}	
			
	public InvoiceListPage(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, InvoiceListPage.class);
	}
	
	public InvoiceListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void open(String url) {
		driver.get(url);
	}
	
	public void close() {
		driver.quit();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}

	
	public void clickOnCol1Row1Field() {
		row1col1.click();
	}
	
	public String getHotelName() {
		return hotelName.getText();
		
		//return null;
	}
	
}
