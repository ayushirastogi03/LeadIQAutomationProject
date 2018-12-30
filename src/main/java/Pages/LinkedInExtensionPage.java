package Pages;

import DriverManager.DriverFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInExtensionPage extends GenericFunctions {

    private WebDriver driver;
    GenericFunctions genericFunctionsobj;

    @FindBy(xpath="//h1[@class='pv-top-card-section__name inline t-24 t-black t-normal']")
    private WebElement leadName;

    @FindBy(xpath="//span[@class='pv-top-card-v2-section__entity-name pv-top-card-v2-section__company-name text-align-left ml2 t-14 t-black t-bold lt-line-clamp lt-line-clamp--multi-line ember-view']")
    private  WebElement leadCompany;

    @FindBy(xpath="//h2[@class='iq name iq-bold']")
    private WebElement leadIQName;

    @FindBy(xpath="//h3[@class='iq company-name']")
    private WebElement leadIQCompany;

    @FindBy(xpath="//div[@class='iq-button action-button primary']")
    private WebElement captureButton;

    private static Logger Log= LogManager.getLogger(LinkedInExtensionPage.class.getName());

    public  LinkedInExtensionPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
        DriverFactory.setPageLoadTimeout();
        genericFunctionsobj=new GenericFunctions(driver);
    }

    public void validateLeadDetails(String leadName,String leadCompany)throws Exception{
        waitUntilElementVisible(leadIQName);
        validateText(leadIQName,leadName);
        validateText(leadIQCompany,leadCompany);
    }

    public void captureProfile() throws Exception{
        clickElement(captureButton);
    }

}
