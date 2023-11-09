package base;

import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import extentlisteners.ExtentListeners;

//limited to cars page this base class

public class CarBase {
	
	public Page page;
	public CarBase(Page page) {
		this.page = page;
	}
	
	public String getCarTitle(String title) {
		
		try {
		ExtentListeners.getExtent().info("Finding car with Heading : " + title);
		return page.locator(BaseClass.OR.getProperty("carTitle_XPATH")).innerText();
		}catch(Throwable t) {
			ExtentListeners.getExtent()
			     .fail("Error while Finding Cars with Heading : " + title + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
			return null;
		}
	}

	 public void getCarBrandNameAndPrices() {
		 
		 Locator carNames = page.locator(BaseClass.OR.getProperty("carNames_XPATH"));
		 Locator carPrices = page.locator(BaseClass.OR.getProperty("carPrices_XPATH"));
		 
		 for(int i=0; i<carPrices.count(); i++)
		 {
			 System.out.println(carNames.nth(i).innerText()+"----Car Price is : "+carPrices.nth(i).innerText());
		 }
	 }
}
