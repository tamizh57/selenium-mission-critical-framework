package com.example.core.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.*;

public class ListenerManager implements ITestListener, ISuiteListener {
    private Logger logger = LoggerFactory.getLogger(ListenerManager.class);

    @Override
    public void onStart(ISuite suite) {
        ReportManager.createExtentReport();
        // Suite-wide setup: could initialize reporting folder, DB, etc.
    }

    @Override
    public void onFinish(ISuite suite) {
        ReportManager.getExtentReport().flush();

    }

    @Override
    public void onTestStart(ITestResult result) {
        // Unique key per test (method + test context)
        String testName = result.getMethod().getMethodName() + "_" + result.getTestContext().getName();

        // Set MDC first so all logs in this thread have the testName
        MDC.put("testName", testName);

        //  Log test start
        logger.info("==== STARTING TEST: {} ====", testName);

        //  Create or reuse ExtentTest node (handles retry scenarios)
        if (!ReportManager.hasTest(testName)) {
            ReportManager.createOrReuseTest(testName).info("Test Started");
        } else {
            // Retry scenario: log the retry attempt
            int retryCount = result.getMethod().getCurrentInvocationCount();
            ReportManager.getTest().warning("Retry attempt #" + retryCount);
            logger.warn("==== RETRY ATTEMPT {} for {} ====", retryCount, testName);
        }
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        // Unique key per test (method + test context)
        String testName = result.getMethod().getMethodName() + "_" + result.getTestContext().getName();
        String message = "Test Passed";
        ReportManager.getTest().pass(message);
        logger.info("==== PASSED: {} ====", testName);

        MDC.clear();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Unique key per test (method + test context)
        String testName = result.getMethod().getMethodName() + "_" + result.getTestContext().getName();
        String message = "Test Failed";
        String screenshot = ScreenshotManager.captureBase64();
        if (screenshot != null) {
            ReportManager.getTest().fail(message + ": " + result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot, "Failure Screenshot").build());
        } else {
            ReportManager.getTest().fail(message + ": " + result.getThrowable());
        }
        logger.error("==== FAILED: {} ====", testName, result.getThrowable());

        MDC.clear();


    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Unique key per test (method + test context)
        String testName = result.getMethod().getMethodName() + "_" + result.getTestContext().getName();
        String message = "Test Skipped";
        ReportManager.getTest().skip(message);
        logger.warn("==== SKIPPED: {} ====", testName);

        MDC.clear();
    }


}
