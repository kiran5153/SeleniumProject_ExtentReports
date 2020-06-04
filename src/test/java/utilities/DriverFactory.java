package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
	private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public static WebDriver initDriver (String browserName, String environment, String...version) {

		WebDriver driver = null;

		switch (browserName.toLowerCase()) {
		case "chrome":
			if	(version.length != 0)
				WebDriverManager.chromedriver().version(version.toString()).setup();
			else
				WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
			break;
		case "firefox":
			if	(version.length != 0)
				WebDriverManager.firefoxdriver().version(version.toString()).setup();
			else
				WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
			break;
		case "ie":
			if	(version.length != 0)
				WebDriverManager.iedriver().version(version.toString()).setup();
			else
				WebDriverManager.iedriver().setup();

			driver = new InternetExplorerDriver();
			break;
		}

		driver.manage().deleteAllCookies();
		driver.get("https://google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		try {			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();		}
		return driver;
	}

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }
    
    public static void setWebDriver(WebDriver driver) {
        if(DriverFactory.getWebDriver()== null) {
            webDriver.set(driver);
        }
    }
	
	public static void tearDownDrivers(){
	        // remove driver instances from ThreadLocal
	        ExtentReport.extentReport.setTestRunnerOutput(Thread.currentThread().getId()+" Test Manager:tear down drivers- "+" entry");
	        try {
	    		if(DriverFactory.getWebDriver()!=null) {
					DriverFactory.getWebDriver().quit();
	    			DriverFactory.webDriver.remove();
	    		}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
}


