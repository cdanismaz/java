package com.w2a.utilities;

import com.w2a.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestUtil extends TestBase {
    public static String screenshotPath;
    public static String screenshotName;

    public static void captureScreenshot() {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenshotName = d.toString().replace(":","_").replace(" ","_")+".png";

        try {
            System.out.println("/Users/cdanismaz/Documents/workspace/java/AutomationTemplate/target/surefire-reports/html"+screenshotName);
            //System.out.println(System.getProperty("user.dir")+"/target/surefire-reports/html/"+screenshotName);
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/target/surefire-reports/html/"+screenshotName));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
