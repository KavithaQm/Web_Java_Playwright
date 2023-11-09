package pages;

import com.microsoft.playwright.Page;

import base.BaseClass;

public class HomePage extends BaseClass {
	
	
	
	
	public HomePage(Page page) {
		super(page);
		
	}


	public void findNewCars() {
		
		mouseHover("newCarsMenu_XPATH");
		click("findNewCars_XPATH");

		
	}
	
	
	public void searchCars() {
		
		
	}
	
	
	public void validateFeaturedCars() {
		
		
		
	}

}
