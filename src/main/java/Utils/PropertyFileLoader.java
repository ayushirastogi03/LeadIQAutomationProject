package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyFileLoader {

    private static PropertyFileLoader instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir")+
            "//src//main//resources//Environment.properties";
    private static String urL;
    private static String leadIQappurl;
    private static String leadUrl;
    private static String Userid;
    private static String pwd;


    public static PropertyFileLoader getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyFileLoader();
                instance.loadData();
            }
        }
        return instance;
    }

    //Get all configuration data and assign to related fields.
    private void loadData() {
        //Declare a properties object
        Properties prop = new Properties();

        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }

        //Get properties from configuration.properties
        urL = prop.getProperty("url");
        leadIQappurl=prop.getProperty("leadIQappUrl");
        leadUrl = prop.getProperty("leadUrl");
        Userid=prop.getProperty("Userid");
        pwd=prop.getProperty("Passwrd");
    }
    public String getUrL(){
        return urL;
    }
    public String getLeadIQappurl(){
        return leadIQappurl;
    }
    public String getLeadUrl(){
        return leadUrl;
    }
    public String getUserid(){ return Userid;}
    public String getPwd(){return pwd;}

}
