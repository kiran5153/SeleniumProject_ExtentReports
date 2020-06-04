package custom_listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry_Listener implements IRetryAnalyzer{

	private int retryCount = 0;
	private int retryMaxCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount<retryMaxCount) {
			retryCount++;
			return true;
		}
		else
			return false;
	}

}
