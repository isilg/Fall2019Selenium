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
import org.testng.annotations.*;
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


    //@Optional - make parameter optional
    //if u dont specify it, testng will require to specify this parameter for every test in xml runner
    @BeforeTest   // @BeforeTest is used to create report
    @Parameters("reportName")  //comes from testng runner file
    public void setupTest(@Optional String reportName){  //I make it optional that means it isn't require to specify this parameter in the xml file
                                                         //cause if I don't make it optional and if test won't this parameter it will give exception
        System.out.println("Report name: "+reportName);  //reportName comes from testng runner file
        report = new ExtentReports();
        //if reportName is null, then reportName will be report.html otherwise it will be reportName variable
        reportName = reportName == null ? "report.html" : reportName; //if it is login ->login.html; if it is calender events, it will be calenderevents.html

        report = new ExtentReports();

        String reportPath = "";
        //location of report file
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\" + reportName;
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/" + reportName;
        }
        //is a HTML report itself
        htmlReporter = new ExtentHtmlReporter(reportPath);
        //add it to the reporter
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("VYTRACK Test Automation Results");
    }


    @AfterTest  // release the report. After @AfterTest report will be generated
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