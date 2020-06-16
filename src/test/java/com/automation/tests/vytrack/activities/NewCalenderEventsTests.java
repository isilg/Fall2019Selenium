package com.automation.tests.vytrack.activities;
import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalenderEventsPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.DateTimeUtilities;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class NewCalenderEventsTests extends AbstractTestBase {

    LoginPage loginPage = new LoginPage();
    CalenderEventsPage calenderEventsPage = new CalenderEventsPage();

    /**
     * Test Case: Default options
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Default owner name should be current user/
     **/
    @Test
    public void defaultOptionsTest() {
        test = report.createTest("Verify default login options");

        LoginPage loginPage = new LoginPage();
        CalenderEventsPage calendarEventsPage = new CalenderEventsPage();

        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();

        Assert.assertEquals(calendarEventsPage.getOwnerName(), calendarEventsPage.getCurrentUserName());

        String actualStartDate = calendarEventsPage.getStartDate();
        String expectedStartDate = DateTimeUtilities.getCurrentDate("MMM d, yyyy");

        Assert.assertEquals(actualStartDate, expectedStartDate);

        test.pass("Default options verified");

    }

    /**
     * 35 minutes until 4:05
     * Test Case: Time difference
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Verify that difference between start and end time is 1 hour
     **/

    @Test
    public void timeDifferenceTest() {
        test = report.createTest("Verify time difference");

        LoginPage loginPage = new LoginPage();
        CalenderEventsPage calendarEventsPage = new CalenderEventsPage();

        loginPage.login();

        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();

        String startTime = calendarEventsPage.getStartTime(); //get start time
        String endTime = calendarEventsPage.getEndTime(); //get end time
        String format = "h:mm a";//format 5:15 AM for example

        long actual = DateTimeUtilities.getTimeDifference(startTime, endTime, format);

        Assert.assertEquals(actual, 1, "Time difference is not correct");

        test.pass("Time difference verified");

    }

    /**
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
    public void verifyColumnNamesTest() {
        test = report.createTest("Verify column names");

        LoginPage loginPage = new LoginPage();
        CalenderEventsPage calendarEventsPage = new CalenderEventsPage();

        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        List<String> expected = Arrays.asList("TITLE", "CALENDAR", "START", "END", "RECURRENT", "RECURRENCE", "INVITATION STATUS");

        Assert.assertEquals(calendarEventsPage.getColumnNames(), expected);
        test.pass("Column names verified");

    }

//    public Object[] eve

    @Test(dataProvider = "calendarEvents")
    public void createCalendarEventTest(String title, String description) {
        //if you have more one test, and 1st pass but others failing,
        //you are getting session id is null exception
        //because driver object was not initialized in time
        //just create page objects inside a test
        LoginPage loginPage = new LoginPage();
        CalenderEventsPage calendarEventsPage = new CalenderEventsPage();

        //only for extent report. To create a test in html report
        test = report.createTest("Create calendar event for " + title);
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.enterCalendarEventTitle(title);
        calendarEventsPage.enterCalendarEventDescription(description);
        calendarEventsPage.clickOnSaveAndClose();

        //verify that calendar event info is correct
        Assert.assertEquals(calendarEventsPage.getGeneralInfoDescriptionText(), description);
        Assert.assertEquals(calendarEventsPage.getGeneralInfoTitleText(), title);

        //for extent report. specify that test passed in report (if all assertions passed)
        test.pass("Calendar event was created successfully!");
    }

    @DataProvider
    public Object[][] calendarEvents() {
        return new Object[][]{
                {"Daily stand-up", "Scrum meeting to provide updates"},
                {"Sprint Review", "Scrum meeting where team discussing previous sprint"},
                {"Sprint Planning", "Scrum meeting where team discussing backlog for following sprint"}
        };
    }
}







//BEN YAZDIM AMA HATA VERDI. YUKARDAKI VASYL'IN CODE
//package com.automation.tests.vytrack.activities;
//import com.automation.pages.LoginPage;
//import com.automation.pages.activities.CalenderEventsPage;
//import com.automation.tests.vytrack.AbstractTestBase;
//import com.automation.utilities.DateTimeUtilities;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Test Case: Default options
// * Login as sales manager
// * Go to Activities --> Calendar Events
// * Click on Create Calendar Event
// * Default owner name should be current user
// */
//public class NewCalenderEventsTests extends AbstractTestBase {
//
//    LoginPage loginPage = new LoginPage();
//    CalenderEventsPage calenderEventsPage = new CalenderEventsPage();
//
//    //If I need to interact w a page I create page object
//    @Test
//    public void defaultOptionsTest(){
//        //create test in the report
//        test = report.createTest("Verify default login options");
//
//        //NoSuchElementException aliyorsan obj leri test lerin icinde create ettik
//        //Bu 2 satiri her test in icine yazdik
//        LoginPage loginPage = new LoginPage();
//        CalenderEventsPage calenderEventsPage = new CalenderEventsPage();
//
//        //navigate, click, and assert
//        loginPage.login();
//        calenderEventsPage.navigateTo("Activities", "Calender Events");
//        calenderEventsPage.clickToCreateCalendarEvent();
//
//        Assert.assertEquals(calenderEventsPage.getOwnerName(), calenderEventsPage.getCurrentUserName());
//
//        String actualStartDate = calenderEventsPage.getStartDate();
//        String expectedStartDate = DateTimeUtilities.getCurrentDate("MMM d, yyyy");
//
//        Assert.assertEquals(actualStartDate, expectedStartDate);
//
//        test.pass("Default options verified");
//    }
//
//    /**
//     * Test Case: Time difference
//     * Login as sales manager
//     * Go to Activities --> Calendar Events
//     * Click on Create Calendar Event
//     * Verify that difference between start and end time is 1 hour
//     **/
//
//    @Test
//    public void timeDifferenceTest() {
//        //create test in the report
//        test = report.createTest("Verify time difference");
//
//        LoginPage loginPage = new LoginPage();
//        CalenderEventsPage calenderEventsPage = new CalenderEventsPage();
//
//        loginPage.login();
//        calenderEventsPage.navigateTo("Activities", "Calender Events");
//        calenderEventsPage.clickToCreateCalendarEvent();
//
//        String startTime = calenderEventsPage.getStartTime();
//        String endTime = calenderEventsPage.getEndTime();
//        String format = "h:mm: a";
//
//        long actual = DateTimeUtilities.getTimeDifference(startTime, endTime, format);
//
//        Assert.assertEquals(actual, 1, "Time difference is not correct");
//
//        test.pass("Time difference verified");
//    }
//
//
//        /**
//         * ::::use qa1::::
//         * Test Case: Verify calendar events table
//         * Login as store manager
//         * Go to Activities --> Calendar Events
//         * And verify that column names displayed:
//         * |TITLE            |
//         * |CALENDAR         |
//         * |START            |
//         * |END              |
//         * |RECURRENT        |
//         * |RECURRENCE       |
//         * |INVITATION STATUS|
//         */
//
//        @Test
//        public void verifyColumnNames(){
//            test = report.createTest("Verify column names");
//
//            LoginPage loginPage = new LoginPage();
//            CalenderEventsPage calenderEventsPage = new CalenderEventsPage();
//
//            loginPage.login();
//            calenderEventsPage.navigateTo("Activities", "Calender Events");
//
//            List<String> expected = Arrays.asList("TITLE", "CALENDAR", "START", "END","RECURRENT" ,"RECURRENCE", "INVITATION STATUS");
//
//           Assert.assertEquals(calenderEventsPage.getColumnNames(), expected);
//           test.pass("Column names verified");
//        }
//
//        @Test(dataProvider = "calenderEvents")
//        public void createCalenderEventsTest(String title, String description){
//            //if u have more than 1 test and 1st test pass but others fail, u get session id is null exception
//            //cause driver object wasn't initialized in time just create page objects inside a test.
//            //Create page obj inside a test itself
//            LoginPage loginPage = new LoginPage();
//            CalenderEventsPage calenderEventsPage = new CalenderEventsPage();
//
//            test = report.createTest("Create calender event for "+title);  //only for extent report. To create a test in html report
//            loginPage.login();
//            calenderEventsPage.navigateTo("Activities", "Calendar Events");
//            calenderEventsPage.clickToCreateCalendarEvent();
//            calenderEventsPage.enterCalendarEventTitle(title);
//            calenderEventsPage.enterCalendarEventDescription(description);
//            calenderEventsPage.clickOnSaveAndClose();
//
//            //verify that calendar event info is correct. description and title are parameters of createCalenderEventsTest() method
//            Assert.assertEquals(calenderEventsPage.getGeneralInfoDescriptionText(), description);
//            Assert.assertEquals(calenderEventsPage.getGeneralInfoTitleText(), title);
//
//            test.pass("Calendar event was created successfully!");   //for extent report. specify that test passed in report (if all assertions passed)
//        }
//
//
//        @DataProvider
//        public Object[][] calenderEvents(){
//            return new Object[][]{
//                    {"Daily stand-up", "Scrum meeting to provide updates"},
//                    {"Sprint Review", "Scrum meeting where team discussing previous sprint"},
//                    {"Sprint Planning", "Scrum meeting where team discussing backlog for following sprint"}
//            };
//        }
//
//
//
//}
