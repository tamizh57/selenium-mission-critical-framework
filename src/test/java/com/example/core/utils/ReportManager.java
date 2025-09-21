package com.example.core.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReportManager {
    private static ExtentReports extentReport;
    private static final Map<String, ExtentTest> testLookupMap = new ConcurrentHashMap<>();
    private static final ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();

    public static synchronized void createExtentReport()
    {
        if(extentReport==null)
        {
            ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter("reports/ExtentReport.html");
            extentSparkReporter.config().setReportName("Automation Report");
            extentSparkReporter.config().setDocumentTitle("Test Results");
            extentReport=new ExtentReports();
            extentReport.attachReporter(extentSparkReporter);
            extentReport.setSystemInfo("Framework", "Selenium + TestNG");
            extentReport.setSystemInfo("Parallel Execution", "Enabled");
        }
    }

    public static synchronized ExtentReports getExtentReport()
    {
        return extentReport;
    }
    // Create new test and store in ThreadLocal
    public static synchronized ExtentTest createOrReuseTest(String testName) {
        ExtentTest test = extentReport.createTest(testName);
        testLookupMap.putIfAbsent(testName,test);
        extentTest.set(testLookupMap.get(testName));
        return getTest();
    }

    // Get current thread’s test
    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }
    public static synchronized boolean hasTest(String testName)
    {
        return testLookupMap.containsKey(testName);
    }
    // Remove after test finishes
    public static synchronized void removeTest() {
        extentTest.remove();
    }
}
