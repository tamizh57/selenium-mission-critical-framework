package com.example.core.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerManager implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ReportManager.createTest(result.getMethod().getMethodName())
                .info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ReportManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ReportManager.getTest().fail("Test Failed: " + result.getThrowable());

        // Example: attach screenshot
        String screenshotPath = "screenshots/" + result.getMethod().getMethodName() + ".png";
        ReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ReportManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.getExtent().flush();
    }
}
