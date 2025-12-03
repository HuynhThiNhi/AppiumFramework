package org.nhihuynh.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager extends ExtentReportManager{
    static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    public static ExtentTest createTest(String testName, String category) {
        ExtentTest extentTest = extentReports.createTest(testName)
                .assignCategory(category);
        test.set(extentTest);

        return test.get();
    }
}
