package Pages;

import DriverManager.DriverFactory;
import Utils.PropertyFileLoader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ExtensionPage extends GenericFunctions {

    private WebDriver driver;
    GenericFunctions genericFunctionsobj;


    private WebDriverWait wait;
    private String successMessage ="Congrats! You've successfully installed LeadIQ.";

    @FindBy(xpath="//div[@class='iq-button action-button action-button accent strong xl']/following-sibling::div//h2")
    private WebElement pageTitle2;
    @FindBy(xpath = "//div/h2[@class='smaller-title']")
    private WebElement pageTitle3;
    private static Logger Log= LogManager.getLogger(ExtensionPage.class.getName());

    public ExtensionPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        DriverFactory.setPageLoadTimeout();
        genericFunctionsobj=new GenericFunctions(driver);
    }

    public void validateInstallPage()throws Exception{
        validatePageTitle(pageTitle2,"Extension Installed");
        waitUntilElementVisible(driver.findElement(By.xpath("//div[contains(text(),'Continue')]")));
        driver.findElement(By.xpath("//div[contains(text(),'Continue')]")).click();
    }

    public void launchWebAppThroughExtension() throws InterruptedException{
        driver.get(PropertyFileLoader.getInstance().getLeadIQappurl());
       // Thread.sleep(2000);
    }
}


