package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.SeleniumServerStandaloneManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class RadioButtons {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/radio_buttons");
        driver.manage().window().maximize();   //maximize browser

        BrowserUtils.wait(2);


        List<WebElement> radioButtons = driver.findElements(By.tagName("input"));

        for (WebElement radioButton : radioButtons){
            //<input type = "radio">  id="red" name="color>  search like //input if it is unique
            String id= radioButton.getAttribute("id");

            //return true if button already clicked
            boolean isSelected = radioButton.isSelected();
            System.out.println(id+" is selected? "+isSelected);



            //If button is eligible to click. Clicking each radio button one by one with for loop
            //Returns true if you can click on the button
            if(radioButton.isEnabled()){
                radioButton.click();
                System.out.println("Click on :: "+ id);
                BrowserUtils.wait(1);

            } else{
                System.out.println("Button is disabled, not clicked : : ");
            }
        }

        driver.quit();
    }
}
