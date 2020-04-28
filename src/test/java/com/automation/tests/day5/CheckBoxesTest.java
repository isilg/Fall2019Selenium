package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckBoxesTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/checkboxes");
        List<WebElement> checkBoxes = driver.findElements(By.tagName("input"));
        BrowserUtils.wait(2);

        //Verify that first checkbox not selected and 2nd one is selected


        if ( (!checkBoxes.get(0).isSelected()) && checkBoxes.get(1).isSelected()){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
        }
        BrowserUtils.wait(2);


        //Click on the first checkbox and verify it's clicked
        checkBoxes.get(0).click();
        BrowserUtils.wait(2);

        if (checkBoxes.get(0).isSelected()){
            System.out.println("TEST PASSED");
        }else {
            System.out.println("TEST FAILED");
        }
        BrowserUtils.wait(2);



        driver.quit();
    }
}
