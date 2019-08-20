package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase{

    @Test(dataProvider="getData")
    public void addCustomer(String fistName, String lastName, String postCode, String alerttext) throws InterruptedException {
        click("addCustButton_CSS");
        type("firstname_CSS", "fistName");
        type("lastname_XPATH", "lastName");
        type("postcode_CSS", "postCode");
        click("addButton_CSS");

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertTrue(alert.getText().contains(alerttext));
        alert.accept();

       // Assert.fail("Customer not added successfully");

    }

    @DataProvider
    public Object[][] getData() {
        String sheetName = "AddCustomerTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows-1][cols];

        for(int rowNum = 2; rowNum <= rows; rowNum++) {
            for(int colNum=0; colNum < cols; colNum++) {
                data[rowNum-2][colNum]  = excel.getCellData(sheetName,colNum, rowNum);
            }

        }

        return data;
    }
}
