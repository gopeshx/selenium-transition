package com.parabank.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();
    private static ExtentReports reports;

    @Override
    public synchronized void onStart(ITestContext context) {
        if (reports != null) {
            return;
        }
        Path reportDirectory = Path.of("target", "extent-reports");
        try {
            Files.createDirectories(reportDirectory);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to create ExtentReports output directory", exception);
        }
        ExtentSparkReporter spark = new ExtentSparkReporter(reportDirectory.resolve("ParaBank.html").toString());
        spark.config().setDocumentTitle("ParaBank UI Automation");
        spark.config().setReportName("ParaBank Feature Test Results");
        reports = new ExtentReports();
        reports.attachReporter(spark);
        reports.setSystemInfo("Application", "ParaBank Demo Banking");
        reports.setSystemInfo("Browser", System.getProperty("browser", "chrome"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        CURRENT_TEST.set(reports.createTest(result.getMethod().getDescription().isBlank()
                ? result.getMethod().getMethodName()
                : result.getMethod().getDescription()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        CURRENT_TEST.get().pass("Passed");
        CURRENT_TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        CURRENT_TEST.get().fail(result.getThrowable());
        CURRENT_TEST.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        CURRENT_TEST.get().skip(result.getThrowable());
        CURRENT_TEST.remove();
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        if (reports != null) {
            reports.flush();
        }
    }
}