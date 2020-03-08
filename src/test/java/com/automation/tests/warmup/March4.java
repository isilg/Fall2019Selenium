package com.automation.tests.warmup;

import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class March4 {
    static WebDriver driver;

    public static void main(String[] args) throws Exception{
        ebayTest();
        amazonTest();
        wikiTest();
    }

/**
 * Go to ebay               --> driver.get("http://ebay.com");
 * enter search term        --> input.sendKeys("java book")  (you'll type sth on the search box)
 * click on search button   --> searchButton.click();
 * print number of results  --> System.out.println(numOfResults.getText());  -> By.tagName("h1")
 *                          --> use tagName because that text is inside h1. Eventhough you see there is a span it is ok.
 *                          --> you can find parent element that contains the text of child element as well.So if you'll
 *                          --> take parent element which is h1,h1 contains inside the span that has the text,once you'll
 *                          --> take h1 it will give you text as well.
 */
    public static void ebayTest() throws Exception {
        //No need to create WebDriver because we created it as a class variable, top of the main
        //WebDriverManager.chromedriver().version("79.0").setup();   //version("79.0") Solving problems cause by Chrome version of 80
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://ebay.com");  //Go to ebay
        driver.findElement(By.id("gh-ac")).sendKeys("macbook");
        Thread.sleep(2000);
        driver.findElement(By.id("gh-btn")).click();
        driver.findElement(By.className("srp-controls__count-heading"));
    }


    /**
     * go to amazon
     * enter search term
     * click on search button
     * verify title contains search term
     */
    public static void amazonTest(){

    }

    /**
     * Go to wikipedia.org
     * enter search term `selenium webdriver`
     * click on search button
     * click on search result `Selenium (software)`- click Search wikipedia box ant type Selenium (software)
     * verify url ends with `Selenium_(software)`
     */
    public static void wikiTest(){

    }
}
