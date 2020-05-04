package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectByIndex {
    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(2);

        //Select a state from State selection dropdown
        Select stateSelect = new Select(driver.findElement(By.id("state")));
        //index is not good, not accurate but u can use sometimes
        stateSelect.selectByIndex(8); //all of them that starts w option is
                             //counted so index 0 is <option value selected="selected>Select a State</option>
                             //although it doesn't show any state!!

        //select the last state
        stateSelect.selectByIndex(stateSelect.getOptions().size()-1);


        BrowserUtils.wait(2);
        driver.quit();
    }
}
