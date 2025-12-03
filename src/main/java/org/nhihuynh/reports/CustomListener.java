package org.nhihuynh.reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener{


    @Override
    public void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extentReport.createTest(result.getMethod().getMethodName())
//                .assignCategory(result.getTestClass().getRealClass().getSimpleName());
//        test.set(extentTest);
        ExtentTestManager.createTest(result.getMethod().getMethodName(), result.getTestClass().getRealClass().getSimpleName());

        ExtentTestManager.getTest().log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
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
//        String reportPath = System.getProperty("user.dir") + "/extent-reports/index.html";
//        ExtentTestManager.getExtentReports(reportPath);
    }

    @Override
    public void onFinish(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + "/extent-reports/index.html";
        ExtentReportManager.getExtentReports(reportPath).flush();

    }
}
