package com.automation.tests.day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * We need to do 2 things:
 *        1- Set up drivers (Ex/chromedriver)
 *        WebDriverManager.chromedriver().setup();
 *        2- Create driver object
 *        ChromeDriver driver = new ChromeDriver();
 */

public class MyFirstSeleniumScript {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        //open some website
        driver.get("http://google.com");
    }
}
