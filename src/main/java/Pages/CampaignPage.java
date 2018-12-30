package Pages;

import DriverManager.DriverFactory;
import Reports.Log;
import Utils.PropertyFileLoader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class CampaignPage extends GenericFunctions{

    private WebDriver driver;
    GenericFunctions genericFunctionsobj;

    private static Logger Log= LogManager.getLogger(CampaignPage.class.getName());

    @FindBy(xpath="//div[@class='iq-button get-started primary']")
    private WebElement GetStartedBtn;

    @FindBy(xpath="//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")
    private WebElement dropdown;

    @FindBy(xpath="//div[contains(text(),'LeadIQ')]")
    private WebElement campaignPageTitle;

    @FindBy(xpath="//h1[@class='pv-top-card-section__name inline t-24 t-black t-normal']")
    private WebElement leadName;

    @FindBy(xpath="//span[@class='pv-top-card-v2-section__entity-name pv-top-card-v2-section__company-name text-align-left ml2 t-14 t-black t-bold lt-line-clamp lt-line-clamp--multi-line ember-view']")
    private  WebElement leadCompany;

    private String campaignPageText="LeadIQ";
    private String expLinkedinUrl="https://www.linkedin.com/in/meisiauw/";
    private String submitBtnloc="//input[@class='login submit-button']";
    private String linkedUserIDloc="//input[@class='login-email']";
    private String linkedPwDloc="//input[@class='login-password']";

    public  CampaignPage(WebDriver driver) throws Exception{
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
        DriverFactory.setPageLoadTimeout();
        genericFunctionsobj=new GenericFunctions(driver);
    }
    public void validateWebAppLaunch()throws Exception{
        waitUntilElementVisible(campaignPageTitle);
        Assert.assertTrue(campaignPageTitle.getText().contains(campaignPageText));
    }

    public void checkCampaignName(DataMapping data)throws Exception{
        clickElement(dropdown);
        String loc ="//span[contains(text(),'"+data.firstName+"')]";
        Thread.sleep(2000);
        if(driver.findElement(By.xpath(loc)).isDisplayed()){
            Log.info("CampaignName is displayed with :"+data.firstName);
        }
        driver.switchTo().defaultContent();
    }

    public void launchLinkedInApp() throws Exception{
        clickElement(GetStartedBtn);
        switchToWindow();
        loginToLinkedIn();
       // Alert alert=driver.switchTo().alert();
        validateUrl(expLinkedinUrl);
    }

    public void loginToLinkedIn() throws Exception{
        String loc="//a[contains(text(),'Sign in')]";
        Thread.sleep(2000);
        WebElement signInBtn=driver.findElement(By.xpath(loc));
        signInBtn.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(linkedUserIDloc)).sendKeys(PropertyFileLoader.getInstance().getUserid());
        Thread.sleep(1000);
        driver.findElement(By.xpath(linkedPwDloc)).sendKeys(PropertyFileLoader.getInstance().getPwd());
        Thread.sleep(1000);
        driver.findElement(By.xpath(submitBtnloc)).click();
        Thread.sleep(2000);
    }

    public String[] switchToLeadIQValidateLinkedInDetails() throws Exception{
        waitUntilElementVisible(leadName);
        String[]linkedInData=new String[2];
        linkedInData[0]=leadName.getText();
        linkedInData[1]=leadCompany.getText();
        switchToWindow();
        DriverFactory.setPageLoadTimeout();
        driver.get(PropertyFileLoader.getInstance().getLeadUrl());
        DriverFactory.setPageLoadTimeout();
        return linkedInData;
    }

}
