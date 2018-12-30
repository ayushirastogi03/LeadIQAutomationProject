package Reports;

public class Log {

    public Log(){

    }

    public static void printTestcaseName(String TestCaseName){
        info("BEGIN TESTCASE");
        info("-----------"+TestCaseName+"------------");
   }

   public static void endTestCase(String TestCaseName){
        info("-----------------END TEST CASE"+TestCaseName+"---------------");}
   public static void error(String errorType,String errorMessage,Exception e){
            error(errorType,errorMessage,e);
   }

   public static void info(String Message){
        info(Message);
   }

   public static void warn(String message){ warn(message); }

   public static void fatal(String message){fatal(message);}

   public static void debug(String message){debug(message);}
}
