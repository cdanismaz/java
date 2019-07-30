package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase{

    @Test(dataProvider="getData")
    public void addCustomer(String fistName, String lastName, String postCode) {
        driver.findElement(By.cssSelector(or.getProperty("addCustButton"))).click();
        driver.findElement(By.cssSelector(or.getProperty("firstname"))).sendKeys(fistName);
        driver.findElement(By.cssSelector(or.getProperty("lastname"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(or.getProperty("postcode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(or.getProperty("addButton"))).click();

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
