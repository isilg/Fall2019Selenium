package com.automation.tests.vytrack;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//It is like a base of every test class. Kind of support for our test classes.It will give us reusability
//It will be like foundation of our test classes. It is abstract so it has to be extended
public abstract class AbstractTestBase {

    //will be visible in the subclass regardless on subclass location (same package or no)
    protected WebDriverWait wait;
    protected Actions actions;

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
    public void tearDown() {
        Driver.closeDriver();
    }
}