package com.w2a.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    /**
     * Initializing
     * WebDriver
     * Properties
     * Logs
     * ExtentReports
     * DB
     * Excel
     * Mail
     * ReportNG, ExtentReports
     * Jenkins
     *
     *
     */

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties or = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src//main//resources//excel//testdata.xlsx");
    public static WebDriverWait wait;
    public ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test;

    @BeforeSuite
    public void setUp() {
        if(driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//properties//Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config file loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//properties//OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                or.load(fis);
                log.debug("OR file loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(config.getProperty("browser").equals("firefox")) {
                //System.setProperty("webdriver.gecko.driver", "gecko");
            }else if(config.getProperty("browser").equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//main//resources//executables//chromedriver");

                driver = new ChromeDriver();
                log.debug("Chrome launched");
            }

            driver.get(config.getProperty("testsiteurl"));
            log.debug("Navigated to : " + config.getProperty("testsiteurl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            wait = new WebDriverWait(driver,5);


        }

    }

    public void click(String locator) {
        if(locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(or.getProperty(locator)));
        } else if(locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(or.getProperty(locator)));
        } else if(locator.endsWith("_ID")) {
            driver.findElement(By.id(or.getProperty(locator)));
        }

        test.log(LogStatus.INFO, "Clicking on :" + locator);
    }

    public void type(String locator, String value) {
        if(locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
        } else if(locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
        } else if(locator.endsWith("_ID")) {
            driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
        }

        driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
        test.log(LogStatus.INFO, "Typing on :" + locator + "entered value : " + value);
    }

    public boolean isElementPresetn(By by) {
        try {
            driver.findElement(by);
            return true;
        }catch(NoSuchElementException e) {
            return false;
        }
    }

    @AfterSuite
    public void tearDown() {
        if(driver != null) {
            log.debug("Test execution completed");
            driver.quit();
        }


    }
}
