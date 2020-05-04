package com.automation.tests.day6;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Xpath {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        BrowserUtils.wait(2);

        //Click button 1 and bring the message after click the button 1
        WebElement btn1 = driver.findElement(By.xpath("//button[@onclick='button1()']"));
        btn1.click();
        BrowserUtils.wait(2);
        //<p id="result" style="color:green">Clicked on button one!</p>
        WebElement result = driver.findElement(By.id("result"));
        System.out.println(result.getText());


        //click on button #2 and bring the message after click the button 2
        WebElement btn2 = driver.findElement(By.xpath("//button[@onclick='button2()']"));
        // or  driver.findElement(By.xpath("//button[text()='Button 2']")); -> visible text
        btn2.click();
          //        <p id="result" style="color:green">Clicked on button two!</p>
         //                  ↓
        System.out.println(result.getText());
        BrowserUtils.wait(2);


        //click on button #3 and bring the message after click the button 3
        driver.findElement(By.xpath("//button[starts-with(@id,'button_')]")).click();
        System.out.println(result.getText());


        //click on button #4 and bring the message after click it
        driver.findElement(By.xpath("//button[contains(@id,'_button')][1]")).click(); //id'si button iceren 2 button vardi : button 4 and Don't click button. Ilki index 1 digeri index 2 olur
        System.out.println(result.getText());


        //click on button #4 and bring the message after click it
        driver.findElement(By.xpath("//button[5]")).click();
        //text e gore xpath yaz
        //driver.findElement(By.xpath("//button[contain(text(),'5')]")).click();
        System.out.println(result.getText());

        BrowserUtils.wait(2);
        driver.quit();
    }
}
