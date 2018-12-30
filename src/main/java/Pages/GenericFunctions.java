package Pages;

import Reports.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GenericFunctions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GenericFunctions(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver,4000,2000);
    }
    private static Logger Log= LogManager.getLogger(ExtensionPage.class.getName());

    public void validatePageTitle(WebElement element,String title) throws Exception{
        Assert.assertEquals(element.getText().equalsIgnoreCase(title),true);
        Log.info("The Page title has been successfully validated :"+title);
    }

    public void validateUrl(String expUrl) throws Exception{
        Assert.assertEquals(driver.getCurrentUrl().equalsIgnoreCase(expUrl),true);
        Log.info("The Url has been successfully launched:"+expUrl);
    }

    public void waitUntilElementVisible(WebElement element) throws Exception{
        Log.info("-----Waiting for the element to be visible");
        wait.until(ExpectedConditions.visibilityOf(element));
        Log.info("The following element is now visible"+element);
    }

    public void validateText(WebElement element,String text)throws Exception{
       Assert.assertEquals(element.getText().equalsIgnoreCase(text),true);
        Log.info("Element :"+element+"contains text : "+text+".");
    }

    public void waitUntilElementIsNotVisible(WebElement element) throws Exception{
        Log.info("Waiting for the Element to be Invisible"+element);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void clickElement(WebElement element)throws Exception{
        waitUntilElementVisible(element);
        element.click();
        Log.info("Element :"+element+" is clicked");
    }
    public void switchToWindow()throws InterruptedException{
        Set<String> handles =new HashSet<String>(driver.getWindowHandles());
        String current=driver.getWindowHandle();
        Iterator<String> handlesIterator=handles.iterator();
        while(handlesIterator.hasNext()){
            String handle=handlesIterator.next();
            if(!handle.equalsIgnoreCase(current)){
                driver.switchTo().window(handle);
                break;
            }
        }
        Thread.sleep(5000);
    }
}
