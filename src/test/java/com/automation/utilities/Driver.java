package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Whenever we call DriverFactory class we get new driver from there
//In case of Driver class, whenever we call it we get same webdriver obj
public class Driver {

    //STEP 1- CREATE PRIVATE STATIC OBJ
    //it is same for everyone
    private static WebDriver driver;

    //STEP-2 MAKE YOUR CONSTRUCTOR PRIVATE
    // if we make constructor private, no one can create obj of Driver class
    // everyone should call static getter method instead
    private Driver(){

    }

    //CREATE PUBLIC STATIC GETTER METHOD TO GET THAT INSTANCE
    public static WebDriver getDriver(){

        //if webdriver obj doesnt exist, create it
        if(driver == null){
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }

        return driver;
    }

    public static void closeDriver(){
        if (driver != null){   //if we still have a driver, if it isn't null
            driver.quit();     //close driver
            driver = null;     //destroy driver
        }
    }

}
