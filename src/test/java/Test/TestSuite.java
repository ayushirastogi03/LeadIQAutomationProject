package Test;

import Pages.*;
import Reports.ExtentTestManager;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestSuite extends BaseTest {
    public static Logger Log = LogManager.getLogger(TestSuite.class.getName());
    String currentTestMethodName;


    @DataProvider(name = "LeadIQDataProvider")
    protected Iterator<DataMapping> dataMappingIterator(Method method) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(0).build();
        List<DataMapping> filteredTestData = new ArrayList<DataMapping>();
        List<DataMapping> testDataList = Poiji.fromExcel(new File("src/test/resources/DataSheet.xlsx"), DataMapping.class, options);
        this.currentTestMethodName = method.getName();
        for (int i = 0; i < testDataList.size(); i++) {
            if (((DataMapping)testDataList.get(i)).TestmethodName.equalsIgnoreCase(this.currentTestMethodName)){
                filteredTestData.add(testDataList.get(i));
            }
        }
        Iterator<DataMapping> itr = filteredTestData.iterator();
        return itr;
    }

    @Test(dataProvider = "LeadIQDataProvider")
    protected void VerifySignUpNewUser2(DataMapping data) throws Exception {
        ExtentTestManager.getTest().setDescription(data.TestmethodName);
        try {
            LeadIQHomePage leadIQHomePage = new LeadIQHomePage(driver);
            leadIQHomePage.enterDetails(data);
            Log.info("Details are validated");
            ExtentTestManager.getTest().log(LogStatus.PASS,"User Details have been filled for a new Application");
            leadIQHomePage.pressSubmitButton();
            ExtentTestManager.getTest().log(LogStatus.PASS,"User Details have been submitted for a new Application");
            ExtensionPage extensionPage=new ExtensionPage(driver);
            extensionPage.validateInstallPage();
            ExtentTestManager.getTest().log(LogStatus.PASS,"ValidateInstallPage","Install Page has been validated successfully");
            extensionPage.launchWebAppThroughExtension();
            ExtentTestManager.getTest().log(LogStatus.PASS,"launchWebAppThroughExtension","The Web App has been launched through extension");
            CampaignPage campaignPage=new CampaignPage(driver);
            campaignPage.validateWebAppLaunch();
            ExtentTestManager.getTest().log(LogStatus.PASS,"validateWebAppLaunch","The Web App has been launched through extension successfully");
            campaignPage.checkCampaignName(data);
            ExtentTestManager.getTest().log(LogStatus.PASS,"checkCampaignName","The Campaign Name is :"+data.firstName);
            campaignPage.launchLinkedInApp();
            ExtentTestManager.getTest().log(LogStatus.PASS,"launchLinkedInApp","The LinkedIn Url is launched through extension:");
            String [] linkedInDetails=campaignPage.switchToLeadIQValidateLinkedInDetails();
            ExtentTestManager.getTest().log(LogStatus.PASS,"switchToLeadIQValidateLinkedInDetails","Switch to LinkedInURl and validate the details of a lead, LeadName :"+linkedInDetails[0]+"Lead Company :"+linkedInDetails[1]);
            LinkedInExtensionPage linkedInExtensionPage=new LinkedInExtensionPage(driver);
            linkedInExtensionPage.validateLeadDetails(linkedInDetails[0],linkedInDetails[1]);
            ExtentTestManager.getTest().log(LogStatus.PASS,"validateLeadDetails","The Linked In details are cross validated from extension Lead IQ"+linkedInDetails[0]+linkedInDetails[1]);
            linkedInExtensionPage.captureProfile();
            ExtentTestManager.getTest().log(LogStatus.PASS,"captureProfile","The lead profile is captured through extension");
        } catch (Exception e) {
            System.out.println("Exception is :"+e.getMessage());
            Assert.fail("VerifySignUpNewUser2() has failed with an exception : " + e.getMessage());
        }

    }
}
