package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException {


            log.debug("Inside Login Test");
            click("blmBtn_CSS");

            Assert.assertTrue(isElementPresetn(By.cssSelector(or.getProperty("addCustButton"))), "Login not successful");

            log.debug(" Login successfully executed.");

            //Assert.fail("Login not successful");
    }
}