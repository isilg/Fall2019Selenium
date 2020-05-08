package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 1-Go to google  2-find searchbox  3-Type java to searchbox 4-get the results,
 * collect all of them  5-And verify that every item contains the word 'Java'
 */


public class SearchTests {
    private WebDriver driver;


   @Test
    public void googleSearchTest(){
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("java", Keys.ENTER);    //name of the searchbox is q
        BrowserUtils.wait(3);
        //since every search item has a tag name <h3>
        //it's the easiest way to collect all of them
        //all titles that contains 'java' have in common the tag h3
        List<WebElement> searchItems = driver.findElements(By.tagName("h3"));
        BrowserUtils.wait(3);
        for(WebElement searchItem : searchItems){
            String var = searchItem.getText();
            if (!var.isEmpty()){    //If there is a text, print it
                System.out.println(var);
                //verify that every search result contains java. IF some of the search results
                //doesn't contain java word, it will fail the test
                Assert.assertTrue(var.toLowerCase().contains("java")); //make all lower case then check if it contains java
                                                                       //Assertion is verification. Without assertion u dont
                                                                       //understand if ur test passed or not. Every test has assertion.
                System.out.println(var.toLowerCase());
                System.out.println();
            }
        }
        //We get some titles them some empty lines then some titles. If there is some titles that doesn't
        //contain "java" it prints empty line for them. Since we dont want the empty lines we use that way.
//        for (WebElement searchItem : searchItems) {
//            System.out.println(searchItem.getText());
//        }
    }

    /**
     * Given user is on the amazon.com page (working on amazon.com page)
     * When user enters "java" as a search item
     * Then user clicks on the search button
     * And user click on the first search item
     * And user verifies that title of the search item contains "java"
     */
    @Test(description = "Search for Java book on Amazon")
    public void amazonSearchTest(){
        driver.get("http://amazon.com");
      //there is a chance that item is not visible so we need to maximize window before clicking on it
        driver.manage().window().maximize();
        BrowserUtils.wait(5);

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java", Keys.ENTER);
        BrowserUtils.wait(5);
        //find all links inside h2 elements, because h2 element is no clickable itself
        //hyperlinks(<a>) are always clickable. Find h2 tag, go inside and collect all links inside h2->//h2//a
        //so h2 elements are not clickable, even though they contain links, that's why, instead of collection all h2 elements
        //we collected all hyperlinks. Every hyperlink represent some search item
        List<WebElement> searchItems = driver.findElements(By.xpath("//h2//a"));

        //click on the first item
        for(WebElement searchItem: searchItems){
            System.out.println("Title: "+searchItem.getText());
        }
        searchItems.get(0).click();
        BrowserUtils.wait(5);

        //find the title on the first item - click on the first book and get the title
        WebElement productTitle = driver.findElement(By.id("title"));
        String productTitleString = productTitle.getText();
        System.out.println(productTitleString);

        Assert.assertTrue(productTitleString.contains("Java"));
    }
    @BeforeMethod
    public void setup(){
        //setup webdriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown(){
        //close browser and destroy webdriver obj
        driver.quit();
    }
}
