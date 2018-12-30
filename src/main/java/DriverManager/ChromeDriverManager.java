package DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;

public class ChromeDriverManager {

    private WebDriver driver;

    private static DesiredCapabilities capabilities = new DesiredCapabilities ();

    public  ChromeDriverManager(){
        setChromeOptionsForExtension();
        driver=new ChromeDriver(capabilities);
        driver.manage().window().fullscreen();
    }

    public WebDriver getDriver(){
        return driver;
    }

    private static void setChromeOptionsForExtension(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addExtensions (new File("src/main/resources/LeadIQ-Lead-Capture_v5.0.2.crx"));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }

}
