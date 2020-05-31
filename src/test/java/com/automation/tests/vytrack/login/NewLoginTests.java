package com.automation.tests.vytrack.login;
import com.automation.pages.LoginPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
       test.info("Login as store manager");  //log some steps
       Assert.assertEquals(loginPage.getWarningMessageText(), "Invalid user name or password.");

       BrowserUtils.getScreenshot("warning_message"); //call getScreenshot() method inside a test, give a name
                                                       //to screenshot. In this case its name is loginPage
       test.pass("Warning message is displayed");
    }



    //to connect our Data Provider with the test we create this
    @Test(dataProvider="credentials")  // we give the name of Data provider to specify this test will receive test data from DataProvider called credentials
    public void loginWithDDT(String userName, String password){    //How can I insert parameters: inside the method signature I put 2 values.
                                                                   // 1th one corresponds 1th column("storemanager85","salesmanager110"),
                                                                   //2nd corresponds 2nd column("UserUser123" column)
        test = report.createTest("Verify page title as" +userName);
        LoginPage loginPage = new LoginPage();  //loginPage is a page obj cause it is obj of page class
        loginPage.login(userName, password);
        test.info("Login as");  //log some steps
        BrowserUtils.wait(2);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
        test.pass("Page title Dashboard was verified");

    }


    //Return type of Data provider can be Object[] or Object[][] or Iterator<Object[]>
    //Object[]   - 1 column with a data
    //Object[][] - 2+
    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
            //We have 3 sets of data(every square bracket is 1 test data) so test will run 3 times(exactly same test)
            {"storemanager85", "UserUser123"},
            {"salesmanager110", "UserUser123"},
            {"user16" , "UserUser123"}
        } ;
    }

}
