package custom_listeners;


import org.testng.IExecutionListener;
import utilities.*;

public class Initiation_Listener implements IExecutionListener  {

	
	@Override
	public void onExecutionStart() {
		ExtentReport.initiateReportLog();
	}

	
	@Override
	public void onExecutionFinish() {
	    DriverFactory.tearDownDrivers();
//        ExtentReport.tearDownReport();
        if(ExtentReport.extentReport!=null)
        	ExtentReport.extentReport=null;
	}
	

}
