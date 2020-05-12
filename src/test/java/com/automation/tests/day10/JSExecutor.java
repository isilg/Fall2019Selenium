package com.automation.tests.day10;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor {
    private RemoteWebDriver driver;    //We didn't use -> private WebDriver driver;
                                       //If we have RemoteWebDriver as a reference type, we don't need to to cast anymore

    @BeforeMethod
    public void setup(){
        //driver = DriverFactory.createDriver("chrome"); -> Returns WebDriver but we didn't use private WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

    @Test
    public void scrollTest(){
        driver.get("http://practice.cybertekschool.com/infinite_scroll");
        driver.manage().window().maximize();
        //javascript executor is an interface so we don't need to create an obj of it
        //but we need to cast our driver object to javascript executor
        //JavascriptExecutor js = (JavascriptExecutor) driver;  //we used RemoteWebDriver as a reference type, so no need to to cast anymore
        for (int i=0; i<10; i++) {
            driver.executeScript("window.scrollBy(0, 250)");
            BrowserUtils.wait(1);
        }

    }

    @Test
    public void scrollToElementTest(){
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
        WebElement link = driver.findElement(By.linkText("Cybertek School"));
        BrowserUtils.wait(2);
        driver.executeScript("arguments[0].scrollIntoView(true)",link);
    }

}
