package testSuite;
import org.testng.annotations.Test;
import basePage.BasePages;

public class Module2_Tests {
	BasePages basePages;

    @Test(groups = {"Regression", "P1"})
    public void Test2_1() throws Throwable{
    	basePages = new BasePages();
	    System.out.println("********* START First Test Case *********");
	    basePages.loginPage.Search();
	    System.out.println("********* END First Test Case *********");
    }

  @Test(groups = {"Regression", "P2"})
    public void Test2_2() throws Throwable{
	  	basePages = new BasePages();
	  	System.out.println("********* START Second Test Case *********");
	    basePages.loginPage.Search();
	    System.out.println("********* END Second Test Case *********");

    }
	
  @Test(groups = {"BVT", "P2"})
   public void Test2_3() throws Throwable{
	  	basePages = new BasePages();
   		System.out.println("********* START Third Test Case *********");
	    basePages.loginPage.Search();
	    System.out.println("********* END Third Test Case *********");
   }

   
}
