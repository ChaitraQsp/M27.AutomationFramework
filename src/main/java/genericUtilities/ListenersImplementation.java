package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation to ITestlistener Interface of TestNG
 * 
 * @author Chaitra M
 *
 */
public class ListenersImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {

		// capture Method name
		String methodName = result.getMethod().getMethodName();
		System.out.println("# " + methodName + " Test Script execution Started #");

		// Intimate the Extent report for Test Execution
		test = report.createTest(methodName);

	}

	public void onTestSuccess(ITestResult result) {

		// capture Method name
		String methodName = result.getMethod().getMethodName();
		System.out.println("# " + methodName + " Test Script PASS #");

		// Log the status as PASS in Extent Report
		test.log(Status.PASS, methodName + " Test Script PASS");

	}

	public void onTestFailure(ITestResult result) {

		// capture Method name
		String methodName = result.getMethod().getMethodName();
		System.out.println("# " + methodName + " Test Script FAIL #");

		// capture Exception
		System.out.println(result.getThrowable());

		// Log the status as FAIL in Extent Report
		test.log(Status.FAIL, methodName + " Test Script FAIL");

		// Log the exception captured to Extent Report
		test.log(Status.WARNING, result.getThrowable());

		// Capture Screen shot
		JavaUtility j = new JavaUtility();
		SeleniumUtility s = new SeleniumUtility();

		String screenshotName = methodName + "-" + j.getSystemDate();

		try {
			
			String path = s.captureScreenShot(BaseClass.sdriver, screenshotName);
			
			//Attach Screen shot to the Report
			test.addScreenCaptureFromPath(path);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

		// capture Method name
		String methodName = result.getMethod().getMethodName();
		System.out.println("# " + methodName + " Test Script SKIP #");

		// capture Exception
		System.out.println(result.getThrowable());

		// Log the status as SKIP in Extent Report
		test.log(Status.SKIP, methodName + " Test Script SKIP");

		// Log the exception captured to Extent Report
		test.log(Status.WARNING, result.getThrowable());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

		System.out.println("# Suite execution Started #");

		// Basic Configuration of Extent Reports
		ExtentSparkReporter esr = new ExtentSparkReporter(
				".\\ExtentReports\\Report - " + new JavaUtility().getSystemDate() + ".html");
		esr.config().setDocumentTitle("Swag Labs Execution Report");
		esr.config().setTheme(Theme.DARK);
		esr.config().setReportName("Automation Execution Report");

		// Report Generation
		report = new ExtentReports();
		report.attachReporter(esr);
		report.setSystemInfo("Base Browser", "Microsoft Edge");
		report.setSystemInfo("Base Platform", "Windows");
		report.setSystemInfo("Base Env", "Testing");
		report.setSystemInfo("Reporter Name", "Chaitra");

	}

	public void onFinish(ITestContext context) {

		System.out.println("# Suite execution Finished #");

		// Generate Extent Report
		report.flush();

	}

}
