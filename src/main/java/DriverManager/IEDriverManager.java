package DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEDriverManager {

    private WebDriver driver;

    public void IEDriverManager(){
        System.setProperty("webdriver.chrome.driver","/Users/ayushirastogi/Downloads/internetExplorer.dmg");
        driver=new InternetExplorerDriver();

    }

    public WebDriver getDriver( ){
        return driver;
    }



}
