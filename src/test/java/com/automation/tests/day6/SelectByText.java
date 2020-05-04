package com.automation.tests.day6;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectByText {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(2);

        //create a webElement obj for dropdown first
        WebElement simpleDropdown = driver.findElement(By.id("dropdown"));
//        //provide webElement obj into constructor
//        Select selectSimpleDropdown = new Select(simpleDropdown);  //as a parameter we insert WebElement itself
//        //In 1 Line
//        //Select selectSimpleDropdown = new Select(driver.findElement(By.id("dropdown")));
//        selectSimpleDropdown.selectByVisibleText("Option 2");    // Selecting a visible text. Inside <select id="dropdown"> we see
//        BrowserUtils.wait(2);                           // <option value="1">Option 1</option>
//        // <option value="2">Option 2</option>
//        // Option 1 and Option 2 are visible texts-->.selectByVisibleText
//        //and select option 1
//        selectSimpleDropdown.selectByVisibleText("Option 1");
//
//        //Select your DOB
//        Select selectYear = new Select(driver.findElement(By.id("year")));
//        Select selectMonth = new Select(driver.findElement(By.id("month")));
//        Select selectDay = new Select(driver.findElement(By.id("day")));
//        BrowserUtils.wait(2);
//
//        //Do the order on the top , first year then month then day
//        selectYear.selectByVisibleText("1988");
//        selectMonth.selectByVisibleText("June");
//        selectDay.selectByVisibleText("8");
//        BrowserUtils.wait(2);
//
//        //Change the date to 25
//        selectDay.selectByVisibleText("25");
//        BrowserUtils.wait(2);
//
//
//        //Choose a state
        Select stateSelect = new Select(driver.findElement(By.id("state")));
//        stateSelect.selectByVisibleText("New York");
//
//        //Verify that u chose New York
//        //option that is currently selected -> getFirstSelectedOption()
//        //!!getFirstSelectedOption() returns a WebElement that's why we need to call getText()
//        String selected = stateSelect.getFirstSelectedOption().getText();
//        if (selected.equals("New York")) {
//            System.out.println("TEST PASSED");
//        } else {
//            System.out.println("FAILED");
//        }
//

        //getOptions(); --> will return all states as WebElement
        List<WebElement> states = stateSelect.getOptions();
        for (WebElement state : states) {
            System.out.println(state.getText());

          //CLICK EACH STATE 1 BY ONE ON THE DROPDOWN
            //stateSelect.selectByVisibleText(state.getText());
        }


        BrowserUtils.wait(2);
        driver.quit();
    }
}
