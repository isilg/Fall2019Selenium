package com.automation.tests.day7;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CssSelector {
    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        BrowserUtils.wait(2);

                                                  // <h3 class="h3">Multiple buttons</h3>
                                                  //             ↓      --> h3 is class_name
        WebElement heading = driver.findElement(By.cssSelector(".h3"));

        //For the Home link on the top left corner --> <a class="nav-link" href="/">Home</a>
        driver.findElement(By.cssSelector(".nav-link"));  //nav-link is class name

        //Find Button 1 by using css
        //<button class="btn btn-primary" onclick="button1()">Button 1</button>
        WebElement btn1 = driver.findElement(By.cssSelector("[onclick='button1()"));

        //button 2
        WebElement btn2 = driver.findElement(By.cssSelector("[name='button2']"));

        //button 3
        WebElement btn3 =driver.findElement(By.cssSelector("[id^='button_']"));

        //button 4
        WebElement btn4 = driver.findElement(By.cssSelector("[onclick='button4()']"));

        //button 5 -->as we refresh the page id is changes!!
        WebElement btn5 = driver.findElement(By.cssSelector("[onclick='button5()']"));

        //button 6  P.S: #id & .className  --> give css selector
        WebElement btn6 = driver.findElement(By.cssSelector("#disappearing_button"));
        BrowserUtils.wait(2);
        btn1.click(); //we can also do click in 1 line
        BrowserUtils.wait(2);
        btn2.click();
        BrowserUtils.wait(2);
        btn3.click();
        BrowserUtils.wait(2);
        btn4.click();
        BrowserUtils.wait(2);
        btn5.click();
        btn6.click();

        BrowserUtils.wait(2);
        driver.quit();

    }
}
