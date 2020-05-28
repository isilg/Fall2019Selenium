package com.automation.tests.vytrack.activities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalenderEventsPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.DateTimeUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Test Case: Default options
 * Login as sales manager
 * Go to Activities --> Calendar Events
 * Click on Create Calendar Event
 * Default owner name should be current user
 */
public class NewCalenderEventsTests extends AbstractTestBase {

    LoginPage loginPage = new LoginPage();
    CalenderEventsPage calenderEventsPage = new CalenderEventsPage();

    //If I need to interact w a page I create page object
    @Test
    public void defaultOptionsTest(){
        //navigate, click, and assert
        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calender Events");
        calenderEventsPage.clickToCreateCalenderEvent();
        Assert.assertEquals(calenderEventsPage.getOwnerName(), calenderEventsPage.getCurrentUserName());

        String actualStartDate = calenderEventsPage.getStartDate();
        String expectedStartDate = DateTimeUtilities.getCurrentDate("MMM dd, yyyy");
        Assert.assertEquals(actualStartDate, expectedStartDate);
    }

    /**
     * Test Case: Time difference
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Verify that difference between start and end time is 1 hour
     **/

    @Test
    public void timeDifferenceTest() {
        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickToCreateCalenderEvent();

        String startTime = calenderEventsPage.getStartTime();
        String endTime = calenderEventsPage.getEndTime();
        String format = "h:mm: a";

        long actual = DateTimeUtilities.getTimeDifference(startTime, endTime, format);

        Assert.assertEquals(actual, 1, "Time difference is not correct");
    }


        /**
         * ::::use qa1::::
         * Test Case: Verify calendar events table
         * Login as store manager
         * Go to Activities --> Calendar Events
         * And verify that column names displayed:
         * |TITLE            |
         * |CALENDAR         |
         * |START            |
         * |END              |
         * |RECURRENT        |
         * |RECURRENCE       |
         * |INVITATION STATUS|
         */

        @Test
        public void verifyColumnNames(){
            loginPage.login();
            calenderEventsPage.navigateTo("Activities", "Calender Events");

            List<String> expected = Arrays.asList("TITLE", "CALENDAR", "START", "END","RECURRENT" ,"RECURRENCE", "INVITATION STATUS");

           Assert.assertEquals(calenderEventsPage.getColumnNames(), expected);
        }




}
