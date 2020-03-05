package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicNavigation {
    public static void main(String[] args) {
        //to start selenium script we need:
        //setup webdriver (browser driver) and create webdriver object
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        //In selenium, everything starts from Webdriver interface
        //If I want to open a web page I just call this driver and I use methods from the driver
        //here we used get() method. Every single script always start with get() method.
        // It is like key to open the door. Get will open website first
        driver.get("http://google.com");
    }
}
