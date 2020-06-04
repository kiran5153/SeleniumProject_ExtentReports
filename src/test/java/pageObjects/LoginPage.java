package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

import utilities.*;

public class LoginPage{
	
	GenericUtility genericUtility;
    ExtentTest extentTest;
    
    
    public LoginPage(){
        PageFactory.initElements(DriverFactory.getWebDriver(), this);
        genericUtility = new GenericUtility();
        this.extentTest = ExtentReport.getExtentTest();
    }

    
    //********************************OBJECTS*********************************************

    String  productTiles_String = "//ul[@class = 'inneraccordion' and contains(@data-bind, 'CategorizedProduct')]/li";

    @FindBy(xpath = "//input[@title='Search']")
    private WebElement searchText;

    @FindBy(xpath = "//input[@class='gNO89b']")
    private WebElement searchButton;


  //**********************************METHODS*****************************************

    public  void Search() throws Exception {
        try {            

            //actionUtility.waitForPageLoad(pageloadTimeout);
        	Thread.sleep(2000);
        	searchText.sendKeys("Automation step by Step");
        	Thread.sleep(2000);
        	searchButton.click();
        	Thread.sleep(2000);

        	extentTest.info("*Info 1*");
        	extentTest.info("*Info 2*");
        	
        }catch (Exception e) {
        	extentTest.warning("Failed to Search");
            throw e;
        }
    }

    
    
}
