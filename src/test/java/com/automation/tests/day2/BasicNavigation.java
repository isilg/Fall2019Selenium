package com.automation.tests.day2;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicNavigation {
    public static void main(String[] args) throws InterruptedException {
        //to start selenium script we need:
        //setup webdriver (browser driver) and create webdriver object
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();  // ChromeDriver is a reference type
        //RemoteWebDriver driver = new SafariDriver();
        //RemoteWebDriver driver = new ChromeDriver();
        //RemoteWebDriver driver = new InternetExplorerDriver();  //RemoteWebDriver is the parent of webdrivers
                                                                  //They all inherited RemoteWebDriver. It is called polymorphism

        //In selenium, everything starts from Webdriver interface
        //If I want to open a web page I just call this driver and I use methods from the driver
        //here we used get() method. Every single script always start with get() method.
        //It is like key to open the door. Get will open website first
        driver.get("http://google.com");
        driver.manage().window().maximize(); //to maximize the browser
        //driver.manage().window().fullscreen();

        Thread.sleep(3000);   //for demo, wait 3 seconds. If you use this add throws declaration. This is why we used throws InterruptedException

        //method that returns page title. You can also see it as tab name, in the browser
        String title = driver.getTitle();   //returns <title>Some title</title> text. It is actual title
        String expectedTitle = "Google";    //It is expected title
        System.out.println("Title is..."+ title);

        if (expectedTitle.equalsIgnoreCase(title)){ //expected title is equal to actual title
            System.out.println("TEST PASSED!");
        }else {
            System.out.println("TEST FAILED");
        }

        //go to another website within the same window
        driver.navigate().to("http://amazon.com");
        //amazon has long title so we just used contain method not equals
        if (driver.getTitle().toLowerCase().contains("amazon")){
            System.out.println("TEST PASSED!");
        }else {
            System.out.println("TEST FAILED!");
        }

        //comeback to google. Instead of writing if statement,line 32, we can write this method
        driver.navigate().back();
        Thread.sleep(3000);  //adding 3 sec between the navigating Google to Amazon
        verifyEquals(driver.getTitle(), "Google");  //calling verifyEquals method, line 55, checking if page title is equals to Google

        //move forward in the browser history, again going to amazon
        driver.navigate().forward();
        Thread.sleep(3000);

        System.out.println("Title: "+driver.getTitle());        //Title is a String
        System.out.println("URL: "+ driver.getCurrentUrl());    // to get URL, URL is a String

        driver.navigate().refresh();  //to reload page
        Thread.sleep(3000);
        //must be at the end
        driver.close();             //to close browser
    }

    //method that verify sth equals. Main method is static, static cannot call nonstatic so we add static to that method
    public static void verifyEquals(String arg1, String arg2){
        if (arg1.equals(arg2)){
            System.out.println("TEST PASSED!");
        }else {
            System.out.println("TEST FAILED!");
        }
    }


}
