package extentlisteners;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;



import utilities.MonitoringMailOutlook;

import utilities.TestConfigOutlook;

public class ExtentListeners implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	
	public static ArrayList<String> passedTests = new ArrayList<String>();
	public static ArrayList<String> failedTests = new ArrayList<String>();
	public static ArrayList<String> totalTestCases = new ArrayList<String>();

	private static ExtentReports extent = ExtentManager.createInstance(".\\reports\\" + fileName);

	public static ExtentTest test;

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
	
	
	public static ExtentTest getExtent() {
		
		return testReport.get();
	}

	public void onTestStart(ITestResult result) {
		totalTestCases.add(result.getMethod().getMethodName());


		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		passedTests.add(result.getMethod().getMethodName());
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		getExtent().pass(m);

	}

	public void onTestFailure(ITestResult result) {
		failedTests.add(result.getMethod().getMethodName());

		ExtentManager.captureScreenshotAsBase64();
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";

		getExtent().fail(result.getThrowable().getMessage());
		
		getExtent().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",
				MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.fileName).build());

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		getExtent().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		getExtent().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}



	String messageBody;
	public void onFinish(ISuite suite) {
		

		if (extent != null) {

			extent.flush();
		}

		
		MonitoringMailOutlook mail = new MonitoringMailOutlook();
		
		
		try {
		messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/PlaywrightProject/Extent_20Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
		try {
			mail.sendMail( TestConfigOutlook.from, TestConfigOutlook.to,
					TestConfigOutlook.subject, TestConfigOutlook.messageBody, TestConfigOutlook.attachmentPath, TestConfigOutlook.attachmentName);
		} catch (AddressException e) {
						e.printStackTrace();
		} catch (MessagingException e) {
						e.printStackTrace();
		}	

	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
}


