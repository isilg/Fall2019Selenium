package com.automation.tests.vytrack.login;
import com.automation.pages.LoginPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

//updated version of LoginPageTest class. This class extends TestBase class so
//@BeforeMethod and @AfterMethod come from TestBase class so no need to write them
public class NewLoginTests extends AbstractTestBase {

    //Login and verify that page title is a "Dashboard"
    @Test
    public void verifyPageTitle(){
        LoginPage loginPage = new LoginPage();  //loginPage is a page obj cause it is obj of page class
        loginPage.login();
        Assert.assertEquals(Driver.getDriver().getTitle(), "Dashboard");
    }


    @Test
    public void verifyWarningMessage(){
       LoginPage loginPage = new LoginPage();  //Loginpage class'ta warning message locator var o zaman o class
                                              //ile baglanti kurmamiz lazim bunuda obj create ederek yapiyoruz
       loginPage.login("wrong", "wrong");
       Assert.assertEquals(loginPage.getWarningMessageText(), "Invalid user name or password.");
    }



}
