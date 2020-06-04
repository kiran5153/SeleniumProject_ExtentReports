package custom_listeners;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import utilities.GenericUtility;
import utilities.DriverFactory;
import utilities.ExtentReport;
import utilities.TestManager;

	public class Execution_Listener implements ITestListener{
		static LocalDateTime startTimeofTCExecution;
		String startTimeStamp;
		ExtentTest extentTestObj;
		String formattedTime;
		ZonedDateTime currentTime;

		@Override
		public void onTestStart(ITestResult result) {
			ExtentReport.extentReport.setTestRunnerOutput(Thread.currentThread().getId()+" Test Execution Listener:before invocation for test - "+result.getMethod().getMethodName());
			String browserName = result.getMethod().getXmlTest().getLocalParameters().get("browserName");
			String environment =  result.getMethod().getXmlTest().getLocalParameters().get("environment");
			ExtentReport.extentReport.setTestRunnerOutput(Thread.currentThread().getId()+" Test Execution Listener:before invocation for test - "+result.getMethod().getMethodName()+" browser name:"+browserName+" environment:"+environment);
	
			WebDriver driver = DriverFactory.initDriver(browserName,environment);
			if (driver!=null) {
				DriverFactory.setWebDriver(driver);
			}else {
				throw new RuntimeException("Unable to produce the driver");
			}       
	
			TestManager.setTestEnvironment(environment);
			TestManager.setBrowserName(browserName);            

			extentTestObj = ExtentReport.extentReport.createTest(result.getMethod().getMethodName());
			if(ExtentReport.getExtentTest() == null){
				ExtentReport.setExtentTest(extentTestObj);
			}
	
			ExtentReport.getExtentTest().assignCategory(result.getInstanceName());
			String groups[] = result.getMethod().getGroups();
			for (int i=0;i<groups.length;i++){
				ExtentReport.getExtentTest().assignCategory(groups[i]);	
			}
	
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			startTimeStamp = dtf.format(now);
			startTimeofTCExecution = LocalDateTime.parse(startTimeStamp, dtf);
		}
	
		@Override
		public void onTestSuccess(ITestResult result) {		
			try {
				ExtentReport.getExtentTest().pass(MarkupHelper.createLabel(result.getMethod().getMethodName() + " - **PASSED***", ExtentColor.GREEN));
			}catch (Exception e){
				e.getStackTrace();
			}finally {
				ExtentReport.flushReport();
				ExtentReport.tearDownReport();
				DriverFactory.tearDownDrivers();
			}			
		}
	
		@Override
		public void onTestFailure(ITestResult result) {	
			try {
				currentTime = ZonedDateTime.now();
				formattedTime = currentTime.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyy_hh_mm_ss"));
				String fileName = GenericUtility.takeScreenShot(result.getMethod().getMethodName(), formattedTime);
				ExtentReport.getExtentTest().fail(MarkupHelper.createLabel(result.getMethod().getMethodName() + " - **FAILED***", ExtentColor.RED));
				ExtentReport.getExtentTest().addScreenCaptureFromPath(fileName);
			}catch (Exception e){
				ExtentReport.getExtentTest().error(e.fillInStackTrace());
			}finally {
				ExtentReport.flushReport();
				ExtentReport.tearDownReport();
				DriverFactory.tearDownDrivers();
			}
		}
	
		@Override
		public void onTestSkipped(ITestResult result) {
			try {
				ExtentReport.getExtentTest().skip(MarkupHelper.createLabel(result.getMethod().getMethodName() + " - **SKIPPED***", ExtentColor.AMBER));
			}catch (Exception e){
				ExtentReport.getExtentTest().error(e.fillInStackTrace());
			}finally {
				ExtentReport.flushReport();
				ExtentReport.tearDownReport();
				DriverFactory.tearDownDrivers();
			}			
		}
	
		@Override
		public void onTestFailedWithTimeout(ITestResult result) {
			System.out.println("3rd Listener - onTestFailedWithTimeout");
		}
	
		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			System.out.println("3rd Listener - onTestFailedButWithinSuccessPercentage");
		}
	
		@Override
		public void onStart(ITestContext context) {
			System.out.println("3rd Listener - onStartContext");
	
		}
	
		@Override
		public void onFinish(ITestContext context) {
			System.out.println("3rd Listener - onFinishContext");
		}

	}
	
	
