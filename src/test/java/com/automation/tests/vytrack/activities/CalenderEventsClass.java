package com.automation.tests.vytrack.activities;

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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CalenderEventsClass {
    private WebDriver driver;
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");

    private String storeManagerUserName = "storemanager85";
    private String storeManagerPassword = "UserUser123";

    private By activitiesBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Activities')]");
    private By calEventsBy = By.xpath("//span[text()=\"Calendar Events\"]");
    private By createCalEventBtnBy = By.xpath("//div[@class='pull-right title-buttons-container']");
    private Actions actions;

    //private By currentUserBy = By.id("user-menu");
    private By currentUserBy = By.cssSelector("#user-menu > a");
    private By ownerBy = By.className("select2-chosen");
    private By titleBy = By.cssSelector("[name='oro_calendar_event_form[title]']");   //DOESNT WORK THERE ARE 2 SAME ID -> By.id("oro_calendar_event_form_title-uid-5eb8ba7f1eecd");
    private By startDateBy = By.cssSelector("[id*='date_selector_oro_calendar_event_form_start-uid']");
    private By startTimeBy = By.cssSelector("[id*='time_selector_oro_calendar_event_form_start-uid']");

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.findElement(usernameBy).sendKeys( storeManagerUserName);
        BrowserUtils.wait(2);
        driver.findElement(passwordBy).sendKeys(storeManagerPassword, Keys.ENTER);
        BrowserUtils.wait(2);

        //hover over Activities
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(activitiesBy)).perform();
        BrowserUtils.wait(2);

        driver.findElement(calEventsBy).click();
        BrowserUtils.wait(2);

    }


    @Test
    public void verifyCreateCalEventBtn(){
        WebElement createCalEventBtn = driver.findElement(createCalEventBtnBy);
        Assert.assertTrue(createCalEventBtn.isDisplayed());
        BrowserUtils.wait(2);
    }


    /**
     * Test Case: Default options
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Default owner name should be current user
     * Default title should be blank
     * Default start date should be current date
     * Default start time should be current time
     */
    @Test(description="Default options")
    public void verifyDefaultValues(){
        //Click on Create Calendar Event
        driver.findElement(createCalEventBtnBy).click();
        BrowserUtils.wait(3);

        //Default owner name should be current user. Default owner name is on the top right corner
        String currentUserName = driver.findElement(currentUserBy).getText();
        BrowserUtils.wait(3);
        String defaultOwnerName = driver.findElement(ownerBy).getText().trim();
        BrowserUtils.wait(3);
        Assert.assertEquals(currentUserName, defaultOwnerName);

        //Default title should be blank
        WebElement titleElement = driver.findElement(titleBy);
        BrowserUtils.wait(3);
        Assert.assertTrue(titleElement.getAttribute("value").isEmpty());

        //Default start date should be current date
        //date time syntax = https://www.journaldev.com/17899/java-simpledateformat-java-date-format
        String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        //input boxes dont have text they have value, use getAttribute()
        String actualDate = driver.findElement(startDateBy).getAttribute("value");
        Assert.assertEquals(actualDate, expectedDate);

        //Default start time should be current time
        // IF WE DONT PUT ZoneId.of("GMT-7"), THIS TEST WILL FAIL CAUSE IT IS EXPECTED LOCAL TIME ZONE, IT IS NOT GIVING THE TIME
        //ACCORDING TO UR LOCATION. IF U R DIFFERENT TIME ZONE IT WILL FAIL --> IT'S A BUG
        String expectedTime = LocalTime.now(ZoneId.of("GMT-7")).format(DateTimeFormatter.ofPattern("h:m a"));
        String actualTime =driver.findElement(startTimeBy).getAttribute("value");
        Assert.assertEquals(actualTime, expectedTime);

    }


    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
