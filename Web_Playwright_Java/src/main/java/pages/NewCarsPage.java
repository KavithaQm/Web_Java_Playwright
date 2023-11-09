

package pages;

import com.microsoft.playwright.Page;

public class NewCarsPage{
	
	public Page page;
	
	public NewCarsPage(Page page) {
		
		this.page = page;
	}
	


public void gotoHonda() {
	
	page.click("//div[normalize-space()='Honda']");
	
	
}

public void gotoToyota() {
	
	page.click("//div[normalize-space()='Toyota']");
	
}
 
public void gotoKia() {
	
	
	page.click("//div[normalize-space()='Kia']");

}

public void gotoBMW() {
	
	page.click("//div[normalize-space()='BMW']");
	
}

}
