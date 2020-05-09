package com.automation.tests.vytrack.login;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;  //STATIC IMPORT OF ALL ASSERTION. We wont need Assert.assertEquals(); -> assertEquals(); will be enough

public class LoginPageTest {

    private WebDriver driver;
    //https is a secured version of http protocol. http hypertext transfer protocol that every single website is using nowadays
    //https is data encrypted, no chance for hackers to retrieve info. http is data as a plain text, very easy to hack it
    private String URL = "https://qa2.vytrack.com/user/login";
    private String username = "storemanager85";  //correct credentials for store manager
    private String password = "UserUser123";
    private By usernameBy = By.id("prependedInput");    //1-By is a class, Instead of String we can use it as By  2-prependedInput is the id of username input box
    private By passwordBy = By.id("prependedInput2");
    // > in css selector means direct child, same as / in xpath
    private By warningMessageBy = By.cssSelector("[class='alert alert-error'] >div");   //<div class="alert alert-error">  but By.className("alert alert-error"); doesnt work o yuzden cssSelector kullandik


    @Test(description = "verify that warning message displays when user enters invalid username")
    public void invalidUsername(){
        driver.findElement(usernameBy).sendKeys("invalidusername");
        driver.findElement(passwordBy).sendKeys("UserUser123", Keys.ENTER);
        BrowserUtils.wait(3);
        WebElement warningElement = driver.findElement(warningMessageBy);
        assertTrue(warningElement.isDisplayed());   //If warning message is not visible, test will fail

        String expected = "Invalid user name or password.";
        String actual = warningElement.getText();
        assertEquals(actual, expected);
    }

    //login as store manager and verify title of the page is Dashboard
    @Test(description = "Login as store manager and verify that title is equals to  'Dashboard' ")
    public void loginAsStoreManager(){
        driver.findElement(usernameBy).sendKeys("storemanager85");
        driver.findElement(passwordBy).sendKeys("UserUser123", Keys.ENTER);
        BrowserUtils.wait(3);

        String expected = "Dashboard";
        String actual = driver.getTitle();  //getTitle() brings title of the page
        assertEquals(actual, expected, "Page title isnt correct");
    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);    //we r not gonna open another webpage so we put the navigation part here
        driver.manage().window().maximize();

    }
    @AfterMethod
    public void teardown(){
        //if webdriver object exist, close the browser, close the session, destroy object
        if(driver != null) {
            driver.quit();  //close the browser, close the session
            driver = null;  //destroys webdriver object completely(get rid of that object from heap).
                            //In java if an object doesnt reference anything, it goes to garbage collector
                            //Garbage collector destroy it and release memory
        }
    }
}
