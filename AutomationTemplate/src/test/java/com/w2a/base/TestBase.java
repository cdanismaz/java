package com.w2a.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
     */

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties or = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");

    @BeforeSuite
    public void setUp() {
        if(driver == null) {
            System.out.println(System.getProperty("user.dir"));

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


        }

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
