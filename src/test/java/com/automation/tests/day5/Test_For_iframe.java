package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Go to that website and print the text inside the frame
 * Step 1- Go to that website --> driver.get("...");
 * Step 2- Switch the frame --> driver.switchTo().frame("....");
 * Step 3- Look for the element --> WebElement textInput = driver.findElement(By.id("...."));
 *                                  System.out.println(textInput.getText());
 * Step 4- U can close the page like that but If u want to exit from the frame -> driver.switchTo().defaultContent();
 */

public class Test_For_iframe {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //Go to http://practice.cybertekschool.com/iframe . And bring the content in the frame
        driver.get("http://practice.cybertekschool.com/iframe");
        BrowserUtils.wait(2);

        //The content inside the frame isnt visible to Selenium. So before looking
        //for that element we need to switch frame to see the inner HTML document.
        //Once we switch we can specify name, id, index, or webelement of the frame
        //****** frame("mce_0_ifr")  -> <iframe ....> in icindeki code'a bak
        driver.switchTo().frame("mce_0_ifr");
        //Now we can look for the element, content is visible
        WebElement textInput = driver.findElement(By.id("tinymce"));
        System.out.println(textInput.getText());
        BrowserUtils.wait(3);


        //before exit from the frame lets write sth. But there is
        //already some text there. First delete them then write sth
        textInput.clear();
        textInput.sendKeys("Hi, hello...");
        BrowserUtils.wait(3);


        //exit from the frame
        driver.switchTo().defaultContent();

        //!!After we exit from frame we cant see the content here and get StaleElementReferenceException!

        //Print the heading on the page top of the frame
        WebElement heading = driver.findElement(By.tagName("h3"));
        System.out.println(heading.getText());


        driver.quit();

    }
}
