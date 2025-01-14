package com.testauto.testsuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports and SparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/extentReport.html");
        sparkReporter.config().setReportName("Ebay Test Suite Report");
        sparkReporter.config().setDocumentTitle("Ebay Test Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a test entry in the report for the current test
        test = extent.createTest(result.getMethod().getMethodName())
                     .assignCategory(result.getTestContext().getName())
                     .assignAuthor("YourName");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test Failed: " + result.getMethod().getMethodName());
        test.fail(result.getThrowable()); // Log the exception
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Save the report
        if (extent != null) {
            extent.flush();
        }
    }
}
