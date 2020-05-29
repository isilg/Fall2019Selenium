package com.automation.tests.vytrack;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

//It is like a base of every test class. Kind of support for our test classes.It will give us reusability
//It will be like foundation of our test classes. It is abstract so it has to be extended
public abstract class AbstractTestBase {

    //will be visible in the subclass regardless on subclass location (same package or no)
    protected WebDriverWait wait;
    protected Actions actions;
    protected ExtentReports report;  //.gitignore a dependency yazdik ExtentReports  icin
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest test;


    @BeforeTest   // @BeforeTest is used to create report
    public void setupTest(){
        report = new ExtentReports();
        String reportPath = "";
        //location of report file
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\report.html";
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/ report.html";
        }
        //is a HTML report itself
        htmlReporter = new ExtentHtmlReporter(reportPath);
        //add it to the reporter
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("VYTRACK Test Automation Results");
    }


    @AfterTest  // release the report
    public void teardownTest(){
        report.flush();
    }

    @BeforeMethod
    public void setup() {
        String URL = ConfigurationReader.getProperty("qa1");  //URL
        //We use Driver class. Whenever we use Driver class it will give us exact same webdriver obj that
        //will use by any other class so it will be unity. We have same webdriver and everybody knows
        //how to call this webdriver so it will be no confusion which webdriver is used by this class. Diff webdriver
        //opens different browser. Thats why we use singleton webdriver to ensure it is always same webdriver
        Driver.getDriver().get(URL);  //OPEN URL, open qa1 environment. Calling Driver class and use getDriver() method
        Driver.getDriver().manage().window().maximize();  //MAXIMIZE WINDOW
        wait = new WebDriverWait(Driver.getDriver(),15);  //WEBDRIVERWAIT SETUP
        actions = new Actions(Driver.getDriver());   //ACTIONS SETUP
    }

    @AfterMethod
    //ITestResult is a class comes from TestNG
    //ITestResult class describes the result of a test.
    //if test failed, take a screenshot
    //no failure - no screenshot
    public void tearDown(ITestResult iTestResult) throws IOException { //if test fails take a screenshot
        if(iTestResult.getStatus()==ITestResult.FAILURE){
            BrowserUtils.getScreenshot(iTestResult.getName());  //screenshot will have a name of test
            String screenshotPath = BrowserUtils.getScreenshot(iTestResult.getName());
            test.addScreenCaptureFromPath(screenshotPath); //attach screenshot
            test.fail(iTestResult.getThrowable());//attach console output
        }
        BrowserUtils.wait(2);
        Driver.closeDriver();
    }
}