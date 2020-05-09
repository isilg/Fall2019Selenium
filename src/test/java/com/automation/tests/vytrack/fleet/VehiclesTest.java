package com.automation.tests.vytrack.fleet;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 under fleet package create a class called VehiclesPageTests
 In this class, you'll need to create @beforemethod with setup and @aftermethod with teardown part.
 Use LoginPageTests class as a reference. Create a test called verifyPageSubTitle
 You'll need to navigate to Fleet --> Vehicles and verify that page subtitle is equals to "All Cars" user assertions for validation.
 */
public class VehiclesTest {
    //https is data encrypted, no chance for hackers to retrieve info. http is data as a plain text, very easy to hack it
    private String URL = "https://qa2.vytrack.com/user/login";
    private String username = "storemanager85";  //correct credentials for store manager
    private String password = "UserUser123";

    private By usernameBy = By.id("prependedInput");    //1-By is a class, Instead of String we can use it as By  2-prependedInput is the id of username input box
    private By passwordBy = By.id("prependedInput2");
    private By fleetBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet')]");
                                  //  ↓      ↓           ↓                                        ↓
                                  //<span class= "title title-level-1">                 //   "Fleet"

    private By subtitleBy = By.className("oro-subtitle");
    private By pageNumberBy = By.cssSelector("input[type='number']");

    private WebDriver driver;



    @Test
    public void verifyPageSubTitle() {
        WebElement subTitleElement = driver.findElement(subtitleBy);
        System.out.println(subTitleElement.getText());
        BrowserUtils.wait(2);

        String expected = "All Cars";
        String actual = subTitleElement.getText();
        BrowserUtils.wait(2);

        Assert.assertEquals(actual , expected);

    }

    /**
     * Given user is on the vytrack landing page
     * When user logs on as a store manager
     * Then user navigates to Fleet --> Vehicles
     * And user verifies that page number is equals to "1"
     */
    @Test
    public void verifyPage(){
        String expected = "1";
        String actual = driver.findElement(pageNumberBy).getAttribute("value");  //1 is not text, attribute
        Assert.assertEquals(actual,expected);
    }



    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

        //login
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtils.wait(5);

        Actions actions = new Actions(driver);
        //perform executes command. Every action should end with perform()
        actions.moveToElement(driver.findElement(fleetBy)).perform(); //move to element instead of click
        BrowserUtils.wait(2);

        //VASYL USED
        //driver.findElement(By.linkText("Vehicles")).click();  //After clicking Fleet there is a bunch of links,
                                                               //click on Vehicles link. If finding locator is har, click fleet->
                                                               //On Vehicle link right click->inspect Element->on the bottom(on the
                                                               //html code below) right click->copy->xpath
        //I USED.....instead of By.linkText("Vehicles") I used xpath
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[2]/div/div/ul/li[3]/a/span")).click();

        //driver.findElement(By.linkText("Vehicles")).click();  //After clicking Fleet there is a bunch of links, click on Vehicles link
        BrowserUtils.wait(2);
    }


    @AfterMethod
    public void teardown() {
        //if webdriver object exist, close the browser, close the session, destroy object
        if (driver != null) {
            driver.quit();  //close the browser, close the session
            driver = null;  //destroys webdriver object completely(get rid of that object from heap).
            //In java if an object doesnt reference anything, it goes to garbage collector
            //Garbage collector destroy it and release memory
        }

    }
}
