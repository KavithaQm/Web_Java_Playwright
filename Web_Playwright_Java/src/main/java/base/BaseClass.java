package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.options.SelectOption;

import extentlisteners.ExtentListeners;

public class BaseClass {
	
	
	public static Page page;
	
	public static Properties OR = new Properties();
	private static FileInputStream fis;
	
	public static CarBase carBase;
	
	//calling getCarTitle() of CarBase class here
	
	
	
	public BaseClass(Page page)  {
		
		this.page = page;
		
		
		// CarBase is initializing 
	carBase = new CarBase(page);
	
		
		try {
			fis = new FileInputStream("./src/main/resources/properties/OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//public void verifyText(String locator, String exe_text) {
//		
//	String act_text = page.locator(BaseClass.OR.getProperty(locator)).innerText();
//	if(act_text.equals(exe_text)) {
//		ExtentListeners.getExtent().info("Actual text: "+act_text + " Matched with "+exe_text);
//	}else {
//		ExtentListeners.getExtent().fail("Actual text: "+act_text + "Not Matched with "+exe_text);
//		Assert.assertEquals(act_text, exe_text);
//	}
//		
//		
//	}
//public void verifyCarBrandName(String locator, String exe_text) {
//	 
//	 String act_text = page.locator(BaseClass.OR.getProperty(locator)).innerText();
//	 if(act_text.equals(exe_text)) {
//			ExtentListeners.getExtent().info("Actual text: "+act_text + " Matched with "+exe_text);
//		}else {
//			ExtentListeners.getExtent().fail("Actual text: "+act_text + "Not Matched with "+exe_text);
//			Assert.assertEquals(act_text, exe_text);
//		}
//	 
//}
//
//     public void verifyCarPrices(String locator1, float exe_price) {
//    	 
//    	 float act_price = page.locator(BaseClass.OR.getProperty(locator1)).count();
//    	 if(act_price==exe_price) {
//    		 
//    		 ExtentListeners.getExtent().info("Actual price: "+act_price + " Matched with "+exe_price);
// 		}else {
// 			ExtentListeners.getExtent().fail("Actual price: "+act_price + "Not Matched with "+exe_price);
// 			Assert.assertEquals(act_price, exe_price);
//    	 }
//	 
//	 
//}
	
	public void click(String locatorKey) {

		try {
			//page.locator(OR.getProperty(locatorKey)).click();
			page.locator(utilities.ExcelReader_locators.excel_locators(locatorKey)).click();
			ExtentListeners.getExtent().info("Clicking on an Element : " + locatorKey);
		} catch (Throwable t) {

			ExtentListeners.getExtent()
					.fail("Clicking on an Element : " + locatorKey + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
		}
	}
	
	
	public void mouseHover(String locatorKey) {

		try {
			//page.hover(OR.getProperty(locatorKey));
			page.hover(utilities.ExcelReader_locators.excel_locators(locatorKey));
			ExtentListeners.getExtent().info("Moving on an Element : " + locatorKey);
		} catch (Throwable t) {

			ExtentListeners.getExtent()
					.fail("Error while Moving on an Element : " + locatorKey + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
		}
	}

	public boolean isElementPresent(String locatorKey) {

		try {
			page.waitForSelector(OR.getProperty(locatorKey), new WaitForSelectorOptions().setTimeout(2000));

			ExtentListeners.getExtent().info("Finding an Element : " + locatorKey);
			return true;
		} catch (Throwable t) {

			ExtentListeners.getExtent().fail("Error while finding an Element : " + locatorKey);

			return false;
		}

	}

	public void type(String locatorKey, String value) {
		try {
			page.locator(OR.getProperty(locatorKey)).fill(value);
			ExtentListeners.getExtent()
					.info("Typing in an Element : " + locatorKey + " and entered the value as :" + value);
		} catch (Throwable t) {

			ExtentListeners.getExtent().fail(
					"Error while typing in an Element : " + t.getMessage() + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
		}
	}

	
	public void select(String locatorKey, String value) {
		try {
			page.selectOption(OR.getProperty(locatorKey),new SelectOption().setLabel(value));
			ExtentListeners.getExtent()
					.info("Selecting an Element : " + locatorKey + " and selected the value as :" + value);
		} catch (Throwable t) {

			ExtentListeners.getExtent().fail(
					"Error while Selecting an Element : " + t.getMessage() + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
		}
	}
	
}
