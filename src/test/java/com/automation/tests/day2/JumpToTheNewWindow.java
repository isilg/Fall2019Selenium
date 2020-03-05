package com.automation.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class JumpToTheNewWindow {

    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/open_new_tab");

        Thread.sleep(5000);

        //every window has some id. This id calls window handle
        //based on window handle, we can switch in between windiws
        String windowHandle = driver.getWindowHandle();
        System.out.println(windowHandle);

        Set<String> windowHandles = driver.getWindowHandles();   //getting all of the current open window id's.
                                                                 //Return type of the method is Set. Set doesnt allow duplicates
        System.out.println(windowHandles);
        System.out.println("BEFORE SWITCH: "+driver.getCurrentUrl());
        //since we know id of original window, we can say switch to sth else that is not equals to old window id
        for ( String windowId : windowHandles){
            if (!windowId.equals(windowHandle)){        //if it is not an old window, then switch
                driver.switchTo().window(windowId);     //to jump to the new window
            }
        }
        System.out.println("AFTER SWITCH: "+driver.getCurrentUrl());
    }
}
