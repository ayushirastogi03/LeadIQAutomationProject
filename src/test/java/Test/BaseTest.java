package Test;

import DriverManager.DriverFactory;
import Reports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;



public class BaseTest  {

    protected WebDriverWait wait;
    protected WebDriver driver;
    protected SoftAssert softAssert;

   public  BaseTest(){

   }


    @BeforeTest
    protected void initialiseTest(ITestContext testContext) throws Exception{
        if(driver==null){
            driver= DriverFactory.getDriver('c');
        }
        DriverFactory.getUrl(driver);
        DriverFactory.setPageLoadTimeout();
    }

    @AfterTest(alwaysRun = true)
    protected void Teardown(){
        if(driver!=null){
            driver.quit();
        }
        System.out.println("After Test Annotation is called");
    }
}
