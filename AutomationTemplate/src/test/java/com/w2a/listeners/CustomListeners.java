package com.w2a.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners extends TestBase implements ITestListener {

    public void onTestStart(ITestResult arg0) {
        test = rep.startTest(arg0.getName().toUpperCase());

    }

    public void onTestSuccess(ITestResult arg0) {
        TestBase.test.log(LogStatus.PASS, arg0.getName().toUpperCase()+"PASS");
        rep.endTest(test);
        rep.flush();

    }

    public void onTestFailure(ITestResult arg0) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {
            TestUtil.captureScreenshot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+ "Failed with exception : "+arg0.getThrowable() );
        test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
        Reporter.log("Click to see screenshot");
        Reporter.log("<a target=\'_blank\' href=\'+TestUtil.screenshotName+'>Screenshots</a>");
        Reporter.log("<br>");
        Reporter.log("<a target=\'_blank\' href=\''+TestUtil.screenshotName+''><img src=\''+TestUtil.screenshotName+' 'height=200 width=200></img></a>");
        rep.endTest(test);
        rep.flush();

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
