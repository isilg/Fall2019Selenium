package com.automation.tests.day10;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsTests {
    private WebDriver driver;
    private Actions actions;

    @BeforeMethod
    public void setup(){
       //driver = DriverFactory.createDriver("chrome");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    @Test
    public void hoverOnImage(){
        driver.get("http://practice.cybertekschool.com/hovers");
        BrowserUtils.wait(2);

//        WebElement img1 = driver.findElement(By.xpath("(//img)[1]"));
//        //hover over the image  ->> Use Actions class
//        //build() -> is used when we've couple of actions, doesn't mandatory. Always end with perform. Perform does the action
//        actions.moveToElement(img1).pause(1000).build().perform();
//
//        WebElement img2 = driver.findElement(By.xpath("(//img)[2]"));
//        actions.moveToElement(img2).pause(1000).build().perform();

        //In 1 line
        WebElement img1 = driver.findElement(By.xpath("(//img)[1]"));
        WebElement img2 = driver.findElement(By.xpath("(//img)[2]"));
        WebElement img3 = driver.findElement(By.xpath("(//img)[3]"));
        actions.moveToElement(img1).pause(1000).moveToElement(img2).pause(1000).moveToElement(img3).build().perform();
        BrowserUtils.wait(2);

        //hover on the first image  and verify that "name: user1" is displayed
        actions.moveToElement(img1).perform();
        WebElement imgText1 = driver.findElement(By.xpath("//h5[text()='name: user1']"));
        // or   WebElement imgText1 = driver.findElement(By.xpath("(//h5)[1]"));
        BrowserUtils.wait(2);
        Assert.assertTrue(imgText1.isDisplayed());

        //move to the 2nd img
        actions.moveToElement(img2).perform();
        WebElement imgText2 = driver.findElement(By.xpath(" (//h5)[2] "));
        Assert.assertTrue(imgText2.isDisplayed());
    }


    @Test
    public void jqueryMenuTest(){
        driver.get("http://practice.cybertekschool.com/jqueryui/menu#");

        //hover on "Enabled" then hover on "downloads" and click on PDF
        WebElement enabled = driver.findElement(By.id("ui-id-3"));
        BrowserUtils.wait(2);
        WebElement downloads = driver.findElement(By.id("ui-id-4"));
        BrowserUtils.wait(2);
        WebElement pdf = driver.findElement(By.id("ui-id-5"));
        actions.moveToElement(enabled).pause(1000).moveToElement(downloads).pause(1000).click(pdf).perform();

    }

    @Test
    public void dragAndDrop(){
        //Accept cookie when website opens otherwise it fails!!!
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
        BrowserUtils.wait(2);

        //If we don't manually click 'Accept cookie' when website opens it fails!!!
        //Not to have to do it we can find Accept Cookie and click it

        WebElement bigCircle = driver.findElement(By.id("droptarget"));
        WebElement smallCircle = driver.findElement(By.id("draggable"));

        //dag and drop the small circle into the big circle
        actions.dragAndDrop(smallCircle, bigCircle).perform();
        BrowserUtils.wait(2);

        String expected = "You did great!";
        String actual = bigCircle.getText();
        BrowserUtils.wait(2);

        Assert.assertEquals(actual , expected);

    }


    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

}







