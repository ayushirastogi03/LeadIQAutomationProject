package Reports;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LogManager {

        static Logger Log = Logger.getLogger(LogManager.class);

        public static void main(String[] args) {
            String log4jConfigFile = System.getProperty("user.dir")
                    + File.separator + "log4j.xml";
            DOMConfigurator.configure(log4jConfigFile);

            Log.debug("this is a debug log message");
            Log.info("this is a information log message");
            Log.error("this is a warning log message");
        }
}

