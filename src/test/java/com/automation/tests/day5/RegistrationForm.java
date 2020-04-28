package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class RegistrationForm {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver =  new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/registration_form");
        BrowserUtils.wait(2);

        //Enter first name
        driver.findElement(By.name("firstname")).sendKeys("katie");
        BrowserUtils.wait(2);

        //Enter last name
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        BrowserUtils.wait(2);

        //Enter username
        driver.findElement(By.name("username")).sendKeys("katsmi");
        BrowserUtils.wait(2);

        //Enter email
        driver.findElement(By.name("email")).sendKeys("katiesmith@gmail.com");
        BrowserUtils.wait(2);

        //Enter password
        driver.findElement(By.name("password")).sendKeys("katskats");
        BrowserUtils.wait(2);

        //Enter phone number
        driver.findElement(By.name("phone")).sendKeys("567-234-1234");
        BrowserUtils.wait(2);

        //Enter gender --> it is a radio button. There are 2 genders and
        //both of them has same name. How can we use the specific one-> css or
        //xpath but we dont know yet so we can use List for now
        List<WebElement> genders= driver.findElements(By.name("gender"));
        genders.get(1).click();
        BrowserUtils.wait(2);

        //Enter date of birth
        driver.findElement(By.name("birthday")).sendKeys("01/01/1985");
        BrowserUtils.wait(2);

        //dropdown ogrenmedik onu geciyoruz

        //choose programming language as Java
        driver.findElement(By.id("inlineCheckbox2")).click();
        //2 tane birden secmek istiyorum c++ and java
        driver.findElement(By.id("inlineCheckbox1")).click();
        BrowserUtils.wait(2);

        //Click submit button
        driver.findElement(By.id("wooden_spoon")).click();
        BrowserUtils.wait(2);

        driver.quit();
    }
}
