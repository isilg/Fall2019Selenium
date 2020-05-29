package com.automation.tests.vytrack.login;
import com.automation.pages.LoginPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

//updated version of LoginPageTest class. This class extends TestBase class so
//@BeforeMethod and @AfterMethod come from TestBase class so no need to write them
public class NewLoginTests extends AbstractTestBase {

    //Login and verify that page title is a "Dashboard"
    @Test
    public void verifyPageTitle(){
        //test - comes from ExtendTest obj
        //we must add to every test at the beginning -> test = report.createTest("Test name");
        test = report.createTest("Verify page title");
        LoginPage loginPage = new LoginPage();  //loginPage is a page obj cause it is obj of page class
        loginPage.login();
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
        //if assertion passed, it will set test status in report to passed
        test.pass("Page title Dashboard was verified");
    }


    @Test
    public void verifyWarningMessage(){
       test = report.createTest("Verify warning message");

       LoginPage loginPage = new LoginPage();   //Loginpage class'ta warning message locator old icin o class'la
                                                //baglanti kurman lazim bunuda obj create ederek yapiyosun
       loginPage.login("wrong", "wrong");
       Assert.assertEquals(loginPage.getWarningMessageText(), "Invalid user name or password.");

       BrowserUtils.getScreenshot("warning_message"); //call getScreenshot() method inside a test, give a name
                                                       //to screenshot. In this case its name is loginPage
       test.pass("Warning message is displayed");
    }



}
