package com.automation.pages.activities;

import com.automation.pages.AbstractPageBase;
import com.automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Test Case: Default options
 * Login as sales manager
 * Go to Activities --> Calendar Events
 * Click on Create Calendar Event
 * Default owner name should be current user
 */

public class CalenderEventsPage extends AbstractPageBase {

    @FindBy(css = "[title='Create Calender event']")
    private WebElement createCalenderEvent;

    @FindBy(className = "select2-chosen")
    private WebElement owner;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")  // ^ is starts with
    private WebElement startDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    private WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    public List<String> getColumnNames(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_end']")));
        return BrowserUtils.getTextFromWebElements(columnNames);
    }


    public String getStartTime(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }

    public String getEndTime(){
        BrowserUtils.waitForPageToLoad(20);
        return endTime.getAttribute("value");
    }

    public String getOwnerName(){
        BrowserUtils.waitForPageToLoad(20);
        //wait for element to be present in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }

    public void clickToCreateCalenderEvent(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calender event']")));
       //until is an object that was inherited from AbstractPageBase class.It returns webElement
        wait.until(ExpectedConditions.elementToBeClickable(createCalenderEvent)).click();
        BrowserUtils.waitForPageToLoad(20);
    }

    public String getStartDate(){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        BrowserUtils.scrollTo(startDate);
        return startDate.getAttribute("value");
    }

}
