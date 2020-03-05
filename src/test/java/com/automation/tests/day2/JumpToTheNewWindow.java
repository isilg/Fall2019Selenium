package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JumpToTheNewWindow {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/open_new_tab");

        Thread.sleep(5000);

        //every window has some id. This id calls window handle
        //based on window handle, we can switch in between windiws
        String windowHandle = driver.getWindowHandle();
        System.out.println(windowHandle);
    }
}
