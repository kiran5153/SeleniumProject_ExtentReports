package utilities;


public class TestManager {
    private static final ThreadLocal<String> testEnvironment = new ThreadLocal<String>();
    private static String browser;

    public static String getTestEnvironment() {
        return testEnvironment.get();
    }

    public static void setTestEnvironment(String environment) {
        testEnvironment.set(environment);
    }

    public static String getBrowserName() {
    	return browser;
    }

    public static void setBrowserName(String browserName){
        browser = browserName;
    }


  
}
