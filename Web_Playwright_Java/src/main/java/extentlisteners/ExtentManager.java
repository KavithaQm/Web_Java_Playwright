package extentlisteners;


import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

import base.BaseClass;


public class ExtentManager {

	private static ExtentReports extent;
	public static String fileName;

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Rahul Arora");
		extent.setSystemInfo("Organization", "way2Automation");
		extent.setSystemInfo("Build no", "W2A-1234");

		return extent;
	}

	
	public static String captureScreenshotAsBase64() {
	    try {
	    	Date d = new Date();
	    	fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
	        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions().setPath(Paths.get("./reports/"+fileName)).setFullPage(true);
	        byte[] screenshotData = BaseClass.page.screenshot(screenshotOptions);

	        // Convert the screenshot data to a base64 string
	        String base64String = Base64.getEncoder().encodeToString(screenshotData);

	        return base64String;
	    } catch (PlaywrightException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}