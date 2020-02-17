package base;


import com.relevantcodes.extentreports.ExtentReports;
import listeners.ExtentManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest  {

    protected static Configuration configurationGet = new Configuration();
    private static Logger logger = Logger.getLogger(Class.class);
    private static DriverFactory driverFactory = new DriverFactory();
    private static ExtentReports extentReports;


    @BeforeMethod(alwaysRun = true)
    public static void beforeMethod() {

        driverFactory.commonBeforeMethodSetProperty();
        driverFactory.commonBeforeMethodImplicitlyWait();
        driverFactory.commonBeforeMethodMaximize();
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("environment"));
    }

    @BeforeSuite(alwaysRun = true)
    public static void setUp(){
           switch (configurationGet.getEnvironment()) {
               case "webChrome":
                   driverFactory.chromeOptions();
                   driverFactory.driverSettingsLocal();
                   logger.info("Suite Before Run : " + "Web Local Chrome Browser");
                   break;
               case "webFirefox":
                   driverFactory.firefoxOptions();
                   driverFactory.driverSettingsLocal();
                   logger.info("Suite Before Run : " + "Web Local Firefox Browser");
                   break;
               case "webSafari":
                   driverFactory.safariOptions();
                   driverFactory.driverSettingsLocal();
                   logger.info("Suite Before Run : " + "Web Local Safari Browser");
                   break;
               case "webInternetExplorer":
                   driverFactory.internetExplorerOptions();
                   driverFactory.driverSettingsLocal();
                   logger.info("Suite Before Run : " + "Web Local InternetExplorer Browser");
                   break;
           }
           logger.info("Trendyol Test Automation");

           try {
               extentReports = ExtentManager.getReporter();
               extentReports.addSystemInfo("Trendyol ", "Selenium TestNG Test Automation");
           } catch (Exception parseExp) {
               parseExp.printStackTrace();
           }

           logger.info("Automation Is Starting");
           }



    @AfterMethod
    public static void afterMethod() {
        driverFactory.getDriver().get().manage().deleteAllCookies();

    }


    @AfterSuite(alwaysRun = true)
    public static void tearDown() {
        if (configurationGet.getEnvironment().equals("webChrome") || configurationGet.getEnvironment().equals("webFirefox") || configurationGet.getEnvironment().equals("webSafari") || configurationGet.getEnvironment().equals("webInternetExplorer")) {
            for (WebDriver webDriver : driverFactory.getDrivers()) {
                //webDriver.close();
                logger.info("Driver close");

            }
        }
    }
}