package listeners;

import base.BaseTest;
import base.DriverFactory;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener extends BaseTest implements ITestListener {

    static DriverFactory driver =new DriverFactory();
    private static Logger logger = Logger.getLogger(Class.class);

    @Override
    public void onFinish(ITestContext testResult) {
        ExtentManager.getReporter().flush();
        logger.info("Finished\n");
    }

    @Override
    public void onStart(ITestContext testResult) {
        PropertyConfigurator.configure("properties/log4j.properties");
        logger.info("Start\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
        ExtentTestManager.getTest().log(LogStatus.UNKNOWN, testResult.getThrowable().toString());
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        logger.fatal("onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        File scrFile = ((TakesScreenshot) driver.getDriver().get()).getScreenshotAs(OutputType.FILE);
        ExtentTestManager.getTest().log(LogStatus.FAIL,"Image",ExtentTestManager.getTest().addBase64ScreenShot(FileUtil.convertImageToBase64(scrFile)));
        ExtentTestManager.getTest().log(LogStatus.FAIL, testResult.getThrowable().toString());
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        logger.info("Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        File scrFile = ((TakesScreenshot) driver.getDriver().get()).getScreenshotAs(OutputType.FILE);
        ExtentTestManager.getTest().log(LogStatus.SKIP,"Image",ExtentTestManager.getTest().addBase64ScreenShot(FileUtil.convertImageToBase64(scrFile)));
        ExtentTestManager.getTest().log(LogStatus.SKIP, testResult.getThrowable().toString());
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
    }

    @Override
    public void onTestStart(ITestResult testResult) {
        ExtentTestManager.startTest(testResult.getMethod().getMethodName());
        logger.info("Test Start-" + testResult.getTestClass().getName());
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        File scrFile = ((TakesScreenshot) driver.getDriver().get()).getScreenshotAs(OutputType.FILE);
        ExtentTestManager.getTest().log(LogStatus.PASS,"Image",ExtentTestManager.getTest().addBase64ScreenShot(FileUtil.convertImageToBase64(scrFile)));
        ExtentTestManager.getTest().log(LogStatus.PASS,"Test Succeded");
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
    }
}
