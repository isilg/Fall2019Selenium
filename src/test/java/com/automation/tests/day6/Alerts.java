package com.automation.tests.day6;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Alerts {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/javascript_alerts");
        BrowserUtils.wait(2);

        //Find all buttons
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        BrowserUtils.wait(2);

        //click 1th button
        buttons.get(0).click();
        BrowserUtils.wait(2);

        //get the text from popup message
        String popupText = driver.switchTo().alert().getText();
        System.out.println(popupText);
        driver.switchTo().alert().accept();
        BrowserUtils.wait(2);

        System.out.println("TEST #1");
        //validate that text after clicking ok on the alert
        String expected ="You successfully clicked an alert";
        String actual = driver.findElement(By.id("result")).getText();
        BrowserUtils.wait(2);

     //It will fail, cause there is a typo  ##BUG## (We did typo on purpose to make it fail and see the result of fail)
        if (expected.equals(actual)){
            System.out.println("Passed");
        }else {
            System.out.println("Failed");
            System.out.println("Expected: "+ expected);
            System.out.println("Actual: "+ actual);
        }
        BrowserUtils.wait(2);


        System.out.println("TEST #2");


        //Click 2nd button & click cancel
        buttons.get(1).click();
        BrowserUtils.wait(2);
        driver.switchTo().alert().dismiss();
        BrowserUtils.wait(2);

        //check if expected and actual results are same
        String expected2 = "You clicked: Cancel";
        String actual2 = driver.findElement(By.id("result")).getText(); //id("result") is the id of the Result after we click OK to the alert
        if (expected2.equals(actual2)){
            System.out.println("Passed");
        }else{
            System.out.println("Failed");
            System.out.println("Expected: "+expected2+",but actual: "+actual2 );
        }

        System.out.println("TEST #3");

        //Click on button 3. Enter text "Hello world!". Verify that text ends with Hello world!
        buttons.get(2).click();
        BrowserUtils.wait(2);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Hello world!");    //driver.switchTo().alert().sendKeys("Hello world!");
        alert.accept();
        String actual3 = driver.findElement(By.id("result")).getText();
        if (actual3.endsWith("Hello world!")){
            System.out.println("Passed");
        }else{
            System.out.println("Failed");
        }

        BrowserUtils.wait(2);
        driver.quit();
    }
}
