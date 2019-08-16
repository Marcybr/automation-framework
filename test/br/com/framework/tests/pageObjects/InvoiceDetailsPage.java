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

public class InvoiceDetailsPage extends TestCase {

	private WebDriver driver;
	
	public static WebElement element = null;

	//InvoiceDetails
	@FindBy(xpath="/html/body/section/div/header/h2")		
	private WebElement invoiceDetailsHdr;
	
	
	//HotelName
	@FindBy(xpath="/html/body/section/div/h4")		
	///html/body/section/div/h4
	
	private WebElement hotelName;
		
	//Invoice#
	@FindBy(xpath="/html/body/section/div/h6")		
	private WebElement invoiceNumber;
		
	//InvoiceDate
	@FindBy(xpath="/html/body/section/div/ul/li[1]/span")		
	private WebElement invoiceDate;
	
	//InvoiceDate2
	@FindBy(xpath="/html/body/section/div/ul/li[1]")		
	private WebElement invoiceDate2;
		
	//BookingStaysDetails
	@FindBy(xpath="/html/body/section/div/h5[1]")		
	private WebElement bookingStayDtl;
		
	//BoookingCode
	@FindBy(xpath="/html/body/section/div/table[1]/tbody/tr[1]/td[2]")		
	private WebElement bookingCode;
	
	
	public static WebElement invoiceDetailPageLoaded(WebDriver driver) {
		element = driver.findElement(By.cssSelector("html body section.content div.container header h2.mt-5"));
		
		System.out.println("Invoice Details Page loaded");
		return element;
	}	
			
	public InvoiceDetailsPage(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, InvoiceDetailsPage.class);
	}
	
	public InvoiceDetailsPage(WebDriver driver) {
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
	
	public String getHotelName() {
		return hotelName.getText();

	
}
}
