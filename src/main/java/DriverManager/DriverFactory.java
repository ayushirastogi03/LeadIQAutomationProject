package DriverManager;

import Utils.PropertyFileLoader;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;


    public static WebDriver getDriver(char typeofBrowser) {
        switch (typeofBrowser) {
            case 'c':
                ChromeDriverManager chromeDriverManager = new ChromeDriverManager();
                driver = chromeDriverManager.getDriver();
                break;

            case 'i':
                IEDriverManager ieDriverManager = new IEDriverManager();
                driver = ieDriverManager.getDriver();
                break;
        }
        return driver;
    }

    public static void getUrl(WebDriver driver) throws InterruptedException{
        driver.get(PropertyFileLoader.getInstance().getUrL());
        Thread.sleep(3000);
    }

    public static void setPageLoadTimeout(){
        driver.manage().timeouts().pageLoadTimeout(4000,TimeUnit.SECONDS);
    }

}
