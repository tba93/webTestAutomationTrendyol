package listeners;

import com.relevantcodes.extentreports.ExtentReports;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtentManager {

    private static ExtentReports extent;
    private final static String filePath = System.getProperty("user.dir")+ "/Reports/" +
            new SimpleDateFormat("dd_MM_yyyy_HHmmss").format(Calendar.getInstance().getTime()) + "//Selenium.html/";

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
        }
        return extent;
    }
}