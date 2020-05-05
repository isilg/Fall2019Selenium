package com.automation.tests.day7;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Xpath {
  //use xpath and go from 'Username text' to 'username input box' and do the same thing for the password
  //we can approach these variables anywhere in our project.!!Be careful don't type in the main, static belongs to class
    public static String userNameLocator = "//label[text()='Username']/following-sibling::input";
    public static String passwordLocator = "//label[text()='Password']/following-sibling::input";
    public static String loginBtnLocator = "//button[contains(text(), 'Login')]";

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/login");
        BrowserUtils.wait(2);

        //Enter tom smith for the username
        driver.findElement(By.xpath(userNameLocator)).sendKeys("tomsmith");

        //Enter password,SuperSecretPassword, for the password
        driver.findElement(By.xpath(passwordLocator)).sendKeys("SuperSecretPassword");
        BrowserUtils.wait(2);

        //Click login button
        driver.findElement(By.xpath(loginBtnLocator)).click();
        BrowserUtils.wait(2);

        BrowserUtils.wait(2);
        driver.quit();

    }
}
