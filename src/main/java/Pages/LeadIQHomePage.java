package Pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LeadIQHomePage extends DataMapping {

    private WebDriver driver;
    private WebDriverWait wait;
    GenericFunctions genericFunctionsobj;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstName;
    @FindBy(xpath="//input[@placeholder='Last Name']")
    private WebElement lastName;
    @FindBy(xpath="//input[@placeholder='Email']")
    private WebElement email;
    @FindBy(xpath = "//input[@placeholder='Company Name']")
    private WebElement CompanyName;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passWord;
    @FindBy(xpath="//input[@placeholder='Confirm Password']")
    private WebElement confirmPwd;
    @FindBy(xpath="//div[contains(text(),'Submit')]")
    private WebElement submitButton;

    private static Logger Log= LogManager.getLogger(LeadIQHomePage.class.getName());

    public  LeadIQHomePage(WebDriver driver) throws Exception{
        this.driver=driver;
        PageFactory.initElements(driver,this);
        genericFunctionsobj=new GenericFunctions(driver);
    }

    public void enterDetails(DataMapping data) throws Exception{
      firstName.sendKeys(data.firstName);
      lastName.sendKeys(data.lastName);
      email.sendKeys(System.currentTimeMillis()+"@gmail.com");
      CompanyName.sendKeys(data.company);
      passWord.sendKeys(data.pwd);
      confirmPwd.sendKeys(data.pwd);
    }

    public void pressSubmitButton()throws Exception{
       genericFunctionsobj.clickElement(submitButton);
       Thread.sleep(5000);
    }


}
