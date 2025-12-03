package org.nhihuynh.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    protected static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports(String reportPath) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Appium Report");
        reporter.config().setDocumentTitle("Automation Results");
        reporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "QA Team");
        extentReports.setSystemInfo("Platform", "android/ios");
        return extentReports;
    }

}
