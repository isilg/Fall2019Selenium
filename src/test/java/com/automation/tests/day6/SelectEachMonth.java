package com.automation.tests.day6;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class SelectEachMonth {
    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        BrowserUtils.wait(2);

        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(2);

        Select selectMonth = new Select(driver.findElement(By.id("month")));
        BrowserUtils.wait(2);

        //Select all months 1 by 1.
        //Hint: getOptions() returns all options from dropdown as List<WebElement>
        //1-Create a list 2-Call it months  3-Choose the dropdown that will give ,me the months
        //Use selectMonth cause it is already find the dropdown by using driver.findElement(By.id("month")
        //(line 21)  4- Then get the each month name by using .getOptions
        List<WebElement> months = selectMonth.getOptions();
        for (WebElement month : months){
            //get the month and select based on that
            //1- selectMonth->goes to the dropdown that will gives me the months
            //2- I choose the months by selectByVisibleText 3-month.getText() will return teh months names
            selectMonth.selectByVisibleText(month.getText());
            BrowserUtils.wait(2);
            //If I wanna print each month's name
            //System.out.println(month.getText());

        }

        driver.quit();


    }
}
