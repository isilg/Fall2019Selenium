package com.automation.tests.warmup;

import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;

public class March4 {
    static WebDriver driver;

    public static void main(String[] args) throws Exception{
        ebayTest();
        amazonTest();
        wikiTest();
    }

/**
 * Go to ebay              --> driver.get("http://ebay.com");
 * enter search term       --> input.sendKeys("java book")  (you'll type sth on the search box)
 * click on search button  --> searchButton.click();
 * print number of results --> System.out.println(numOfResults.getText());  -> By.tagName("h1")
 *                         --> use tagName because that text is inside h1. Eventhough you see there is a span it is ok.
 *                         --> you can find parent element that contains the text of child element as well.So if you'll
 *                         --> take parent element which is h1,h1 contains inside the span that has the text,once you'll
 *                         --> take h1 it will give you text as well.
 */
    public static void ebayTest() throws Exception {
        //No need to type WebDriver because we created it as a class variable, top of the main
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://ebay.com");  //Go to ebay
        driver.findElement(By.id("gh-ac")).sendKeys("java book");

        Thread.sleep(2000);

        driver.findElement(By.id("gh-btn")).click();

        WebElement searchResults = driver.findElement(By.tagName("h1"));

        //Print 37,431 results for java book
        //System.out.println(searchResults.getText());
        //If I only wanna see the number like 37,431
        System.out.println(searchResults.getText().split(" ")[0]);

        driver.quit();
    }


    /**
     * go to amazon
     * enter search term
     * click on search button
     * verify title contains search term   (verify that search term(whatever you search) is in the title of the page)
     */
    public static void amazonTest() throws Exception{
        driver = DriverFactory.createDriver("chrome");
        driver.get("https://www.amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java book", Keys.ENTER);

        Thread.sleep(2000);

        String title = driver.getTitle();
        if(title.contains("java book")){
            System.out.println("TEST PASSED!");
        }else {
            System.out.println("TEST FAILED!");
        }

        driver.quit();
    }



    /**
     * Go to wikipedia.org
     * enter search term 'selenium webdriver'
     * click on search button
     * click on search result 'Selenium (software)' --> after searching selenium webdriver, click on the result of Selenium (software) (it is the first search result on the page)
     * verify url ends with 'Selenium_(software)'
     */

    public static void wikiTest() throws Exception{
        driver = DriverFactory.createDriver("chrome");
        //Go to wiki
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        //enter search term 'selenium webdriver' & click on search button
        driver.findElement(By.id("searchInput")).sendKeys("selenium webdriver", Keys.ENTER);

        Thread.sleep(2000);

        //click on search result 'Selenium (software)'
        driver.findElement(By.partialLinkText("Selenium (software)")).click();

        Thread.sleep(2000);

        //current url : https://en.wikipedia.org/wiki/Selenium_(software)
        //to get link as a String
        String link = driver.getCurrentUrl();

        //verify url ends with Selenium_(software)
        if (link.endsWith("Selenium_(software)")){
            System.out.println("TEST PASSED!");
        }else{
            System.out.println("TEST FAILED!");
        }


            driver.quit();
    }
}
