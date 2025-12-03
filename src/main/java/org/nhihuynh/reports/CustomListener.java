package org.nhihuynh.reports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.nhihuynh.utils.ScreenShotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener{


    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.createTest(result.getMethod().getMethodName(), result.getTestClass().getRealClass().getSimpleName());

        ExtentTestManager.getTest().log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            AppiumDriver driver = (AppiumDriver) (result.getTestClass().getRealClass().getField("driver")).get(result.getInstance());
            String base64Screenshot = ScreenShotUtil.getBase64Screenshot(driver);
            ExtentTestManager.getTest().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

        } catch (NoSuchFieldException e) {
            ExtentTestManager.getTest().log(Status.INFO, "No such field driver");
            ExtentTestManager.getTest().fail(result.getThrowable());
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            ExtentTestManager.getTest().fail(result.getThrowable());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + "/extent-reports/index.html";
        ExtentReportManager.getExtentReports(reportPath).flush();

    }
}
