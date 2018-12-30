package Reports;

import com.relevantcodes.extentreports.ExtentReports;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir+"//ExtentReports//ExtentReportResults"+dateTimeGenerator()+".html", true);
        }
        return extent;
    }

    public static String dateTimeGenerator(){
        Format formatter=new SimpleDateFormat("ddMMYY_HHmmsss");
        Date date=new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}