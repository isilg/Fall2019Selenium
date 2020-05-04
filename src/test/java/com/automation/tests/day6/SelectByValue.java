package com.automation.tests.day6;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectByValue {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(2);

        //Select a state from State selection dropdown
        Select stateSelect = new Select(driver.findElement(By.id("state")));
        stateSelect.selectByValue("NY");    //stateSelect.selectByVisibleText("New York");
        BrowserUtils.wait(2);

        //verify if you chose New York
        String expected = "New York";
        String actual = stateSelect.getFirstSelectedOption().getText();
        if(expected.equals(actual)){
            System.out.println("PASSED");
        }else {
            System.out.println("Failed");
        }



        driver.quit();


    }
}
