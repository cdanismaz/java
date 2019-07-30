package com.w2a.listeners;

import com.w2a.utilities.TestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        TestUtil.captureScreenshot();
        Reporter.log("Click to see screenshot");
        Reporter.log("<a target=\'_blank\' href=\'+TestUtil.screenshotName+'>Screenshots</a>");
        Reporter.log("<br>");
        Reporter.log("<a target=\'_blank\' href=\''+TestUtil.screenshotName+''><img src=\''+TestUtil.screenshotName+' 'height=200 width=200></img></a>");
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
