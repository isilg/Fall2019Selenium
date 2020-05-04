package com.automation.tests.day6;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class NoSelectDropdown {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver("chrome");
        BrowserUtils.wait(2);
        driver.get("http://practice.cybertekschool.com/dropdown");


        //Get all the links from 'Select a website' dropdown
           List<WebElement> allLinks = driver.findElements(By.className("dropdown-item"));   //<a class="dropdown-item"....>dropdown-item is a common class name for all links
               for (WebElement link : allLinks) {//getText() -> return the name of websites like Google, Amazon...
                   System.out.println(link.getText()+": "+link.getAttribute("href"));//getAttribute() -> return the website address, http://www.amazon.com
                   BrowserUtils.wait(2);
               }


        //Select a website from the dropdown.
        //First make it visible(by finding the dropdown) then make a choice
        //This looks like a dropdown but it is not a select dropdown, different than the dropdowns below on the website!!!
        //1-Find that dropdown   2-expand it -> by clicking so we can chose a website
        //3-select an option-> they are link cause they are represented by href attribute(href="http://.....")
        //<a> link is hyper link and hyper link has visible text and web address like href="http://....."
        driver.findElement(By.id("dropdownMenuLink")).click(); //expand dropdown -> .click()
        BrowserUtils.wait(2);
        driver.findElement(By.linkText("Amazon")).click(); //click on option



        BrowserUtils.wait(2);
        driver.quit();

    }
}
