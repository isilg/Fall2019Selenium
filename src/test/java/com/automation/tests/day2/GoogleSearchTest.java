package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {

    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
        Thread.sleep(2000);
        //This is the method that will find element based on locator(based on our search criteria).
        //Make sure that element exists on the page. If element doesn't exist, you'll get exception
        //WebElement object represents one particular element from page. We find web elements based on locators
        //name is one of the locators available in Selenium webdriver
        //By.name("q) --> name="q"
        //name is one of the Selenium locators
        WebElement search = driver.findElement(By.name("q"));
        //once we found an element, how to enter text?
        //to enter text use sendKeys() method. How to press Enter after entering text? -> use Keys.ENTER
        //I want Selenium to press Enter instead of finding the Search button and clicking on it(Selenium instead of clicking Google search button , click enter)
        search.sendKeys("Java", Keys.ENTER);
        Thread.sleep(2000);
        //<a> element is called link.
        //visible text of this link ("News"), can be used by selenium to find this element--> "News" is on the Google html code
        WebElement news = driver.findElement(By.linkText("News"));
        news.click();   //if you find news click on it
        Thread.sleep(2000);

        driver.quit();
    }
}
