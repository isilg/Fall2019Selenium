package com.automation.tests.day11;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor2 {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver= DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
    }


    @Test
    public void verifyTitle(){
        //title of the webpage is 'Practice'. Go to <head> in the html code and look for the <title> tag
        String expected = "Practice";
        //we create javascriptexecutor obj by casting webdriver obj
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //executeScript() method executes javascript code
        //we provide js code as a string
        //return document.title is a javascript code. document represents HTML page
        //Without toString() , we need to cast --> String actual = (String) js.executeScript("return document.title");
        String actual = js.executeScript("return document.title").toString();

        Assert.assertEquals(actual, expected);

    }


    @Test
    public void clickTest(){

        //Go to 'Multiple Button' link and click it by using JavascriptExecutor
        WebElement link = driver.findElement(By.linkText("Multiple Buttons"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //after "" we can list webelements that will be used as part of our javascript code
        //there is no limit how many webelement we'll use -> js.executeScript("", link,link,link);
        //use index to get specified element. First we find button 6 and use it in argument[0]
        //so argument[0]is button 6. 0 because it is a first webElement after comma. It will
        //goes like 0 1 2 etc. If we use 1 webelement we need to use index:
        js.executeScript("arguments[0].click()", link);

        //Click on button6
        WebElement button6 = driver.findElement(By.id("disappearing_button"));
        js.executeScript("arguments[0].click()", button6);
        BrowserUtils.wait(2);

        WebElement result = driver.findElement(By.id("result"));

        Assert.assertEquals(result.getText(), "Now it's gone!");

    }


    @Test(description = "Enter a text with javascriptExecutor")
    public void textInputText(){

        //click form authentication link
        driver.findElement(By.linkText("Form Authentication")).click();
        BrowserUtils.wait(2);


        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        BrowserUtils.wait(2);
        WebElement loginbtn = driver.findElement(By.id("wooden_spoon"));
        BrowserUtils.wait(2);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //to enter text->set attribute "value"
        js.executeScript("arguments[0].setAttribute('value', 'tomsmith')", username);
        js.executeScript("arguments[0].setAttribute('value', 'SuperSecretPassword')", password);
        js.executeScript("arguments[0].click()", loginbtn);

        BrowserUtils.wait(4);

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String subheader = js.executeScript("return document.getElementsByClassName('subheader')[0].textContent").toString();
        Assert.assertEquals(subheader, expected);

    }


    @Test
    public void scrollToElement(){
        WebElement link = driver.findElement(By.linkText("Cybertek School"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //scrollIntoView(true) -> use this method to scroll till the element will be visible(till u find the element)
        js.executeScript("arguments[0].scrollIntoView(true)", link);

    }


    @Test
    public void scrollTest(){
        driver.get("http://practice.cybertekschool.com/infinite_scroll");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i =0; i<5; i++) {
            js.executeScript("window.scrollBy(0,100)");
            BrowserUtils.wait(1);
        }
    }


    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

}
