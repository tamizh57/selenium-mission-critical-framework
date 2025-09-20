package com.example.core.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    private static ExtentReports extentReport;
    private static final ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();

    public static synchronized ExtentReports getExtent()
    {
        if(extentReport==null)
        {
            ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter("target/ExtentReport.html");
            extentSparkReporter.config().setReportName("Automation Report");
            extentSparkReporter.config().setDocumentTitle("Test Results");
            extentReport=new ExtentReports();
            extentReport.attachReporter(extentSparkReporter);
            extentReport.setSystemInfo("Framework", "Selenium + TestNG");
            extentReport.setSystemInfo("Parallel Execution", "Enabled");
        }
        return extentReport;
    }
    // Create new test and store in ThreadLocal
    public static synchronized ExtentTest createTest(String testName) {
        ExtentTest test = getExtent().createTest(testName);
        extentTest.set(test);
        return test;
    }

    // Get current thread’s test
    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    // Remove after test finishes
    public static synchronized void removeTest() {
        extentTest.remove();
    }
}
