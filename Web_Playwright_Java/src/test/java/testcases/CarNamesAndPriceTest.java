package testcases;


import java.util.Hashtable;

import org.testng.annotations.Test;

import base.BaseClass;
import base.BaseTest;
import pages.HomePage;
import pages.NewCarsPage;
import utilities.Constants;
import utilities.DataProviders;
import utilities.DataUtil;
import utilities.ExcelReader;


public class CarNamesAndPriceTest extends BaseTest{
	
	
	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void carNamesAndPrices(Hashtable<String,String> data) {
		
		
		// creating object of excel reader
		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("CarWaleSuite", "carNamesAndPrices", data.get("Runmode"), excel);
        
        
		browser = getBrowser(data.get("browser"));
		navigate(browser,Constants.URL);
		
		
		
		HomePage home = new HomePage(page);
		home.findNewCars();
		
		NewCarsPage cars = new NewCarsPage(page);
		
		if(data.get("carBrand").equals("kia")) {
			cars.gotoKia();
		}else if(data.get("carBrand").equals("toyota")) {
			cars.gotoToyota();
		}else if(data.get("carBrand").equals("honda")) {
			cars.gotoHonda();
		}else if(data.get("carBrand").equals("bmw")) {
			cars.gotoBMW();
		}
		
	

		  System.out.println(BaseClass.carBase.getCarTitle(data.get("carTitle")));		  
		  BaseClass.carBase.getCarBrandNameAndPrices();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
}
}
