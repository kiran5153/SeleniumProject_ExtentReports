package pageObjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

import utilities.*;

import java.util.List;

public class GooglePage{
	
	GenericUtility genericUtility;
    ExtentTest extentTest;
    WebDriver driver;
    
    public GooglePage(){
        PageFactory.initElements(DriverFactory.getWebDriver(), this);
        genericUtility = new GenericUtility();
        this.extentTest = ExtentReport.getExtentTest();
        this.driver = DriverFactory.getWebDriver();
    }

    
    //********************************OBJECTS*********************************************

    String  productTiles_String = "//ul[@class = 'inneraccordion' and contains(@data-bind, 'CategorizedProduct')]/li";

    @FindBy(xpath = "//input[@title='Search']")
    private WebElement searchText;

    @FindBy(css = "input[title='Search']")
    private WebElement searchText1;

    @FindBy(xpath = "//input[@class='gNO89b']")
    private WebElement searchButton;
    
    @FindBy(xpath = "(//h3[@class='LC20lb DKV0Md'])[1]")
    private WebElement searchedLink;

    @FindBy(xpath = "(//h3[@class='LC20lb DKV0Md'])[1]")
    private List<WebElement> searchedLinka;

  //**********************************METHODS*****************************************

    
    public  void VerifyGoogleIsOpened()  {
        try {            
        	assertEquals(DriverFactory.getWebDriver().getTitle(), "Google", "Google Title Mismatch");         	
        }catch (Exception e) {
        	extentTest.warning(e.fillInStackTrace() + " Google Title Mismatch");
            throw e;
        }
    }
     
    public  void EnterSearchGoogleText(String searchgoogleText) {
        try {            
        	searchText.sendKeys(searchgoogleText);
        }catch (Exception e) {
        	extentTest.warning("Failed to Search" + searchText);
            throw e;
        }
    }

    public  void SearchGoogle() throws Exception {
        try {            
        	searchButton.click();
        	Thread.sleep(2000);
        }catch (Exception e) {	
        	extentTest.warning("Failed to Search" + searchText);
            //throw e;
        }
    }
    
    public  void ClickSearchedLink() throws Exception {
        try {            
        	searchedLink.click();
        	Thread.sleep(2000);
        }catch (Exception e) {	
        	extentTest.warning("Failed to Search" + searchText);
            //throw e;
        }
    }
    
    public  void ValidateAutomationStepByStep(String exptitle) {
        try {            
        	assertEquals(DriverFactory.getWebDriver().getTitle(), exptitle, "Automation Step By Step - Title Mismatch");         	
        }catch (Exception e) {
        	extentTest.warning(e.fillInStackTrace() + " Google Title Mismatch");
        }
    }
        
    public  void Search() throws Exception {
        try {            

            //actionUtility.waitForPageLoad(pageloadTimeout);
        	Thread.sleep(2000);
        	searchText.sendKeys("Automation step by Step");
        	Thread.sleep(2000);
        	searchButton.click();
        	Thread.sleep(2000);
        	
        	//actionUtility.takeScreenShot(methodName, timeStamp)
        	
        	extentTest.info("*Info 1*");
        	extentTest.info("*Info 2*");
        	
        }catch (Exception e) {
        	extentTest.warning("Failed to Search");
            throw e;
        }
    }

    
    
}
