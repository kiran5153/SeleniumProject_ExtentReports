package utilities;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class GenericUtility {

	Actions action = null;	  

	public GenericUtility() {
		this.action = new Actions(DriverFactory.getWebDriver());
	}

	public static String takeScreenShot(String methodName, String timeStamp) throws Exception {
		// captures the screen shot
		String reportLocation = ".//target/extent_reports/Screenshots/";
		String fileName = reportLocation +methodName+"_"+timeStamp+".png";
		try {
			EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(DriverFactory.getWebDriver());
			File scrFile = eventFiringWebDriver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(fileName));
			System.out.println("***Placed screen shot in "+reportLocation+" ***");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		return fileName;
	}


}
