package com.automation.tests.day3;

import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.xml.transform.Source;
import java.sql.SQLOutput;

public class FindElementsPractice {

    public static void main(String[] args) throws Exception {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/sign_up");         //Go to the website
        WebElement fullName = driver.findElement(By.name("full_name"));   //Find an element
        fullName.sendKeys("Mister Twister");             //Enter text
        Thread.sleep(2000);

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("sdet@cybertek.com");
        Thread.sleep(2000);

        WebElement signUp = driver.findElement(By.name("wooden_spoon"));  //button name is wooden_spoon
        signUp.click();     //click the button. If you see type="submit", you can use signUp.submit() instead of click()
        Thread.sleep(2000);

        //Once we submitted we'll read actual text by tag name, class name or name
        String expected = "Thank you for signing up. Click the button below to return to the home page.";

        WebElement message = driver.findElement(By.className("subheader"));
     //               ⬇️  Web element(message) goes here
       String actual=message.getText();   //getText() returns visible text. Anything in between opening and closing tags is a visible text

        //If the text that message(Web Element) return equals to actual text then TEST PASSED!
        //expected => message that we expected. actual => text on the web page
        if (expected.equalsIgnoreCase(actual)){
            System.out.println("TEST PASSED!");
        }else{
            System.out.println("TEST FAILED!");
        }

        driver.quit();
    }
}
