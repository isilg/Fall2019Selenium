package com.automation.tests.day3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementById {

    public static void main(String[] args) throws Exception{

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/login");

        driver.findElement(By.name("username")).sendKeys("tomsmith");
        Thread.sleep(2000);

        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        //ERROR. sendKeys() doesn't return webElement it is void so you cannot use it in the right
        //side of the = sign and type WebElement on the left side of equal sign
        //WebElement password = driver.finElement(By.name("password")).sendKeys("SuperSecretPassword");
        Thread.sleep(2000);

        //no name for the login button on the html code so we r using id
        driver.findElement(By.id("wooden_spoon")).click();
        Thread.sleep(2000);

        String expected = "Welcome to the Secure Area. When you are done click logout below.";

        //driver.findElement(By.tagName("h4")) != String
        // it is not String so how can we get String? => If we add .getText(), we'll get String output
        String actual= driver.findElement(By.tagName("h4")).getText();

        if(expected.equals(actual)){
            System.out.println("TEST PASSED!");
        }else{
            System.out.println("TEST FAILED!");
        }

        //Lets click on Logout button. It looks like a button but
        //it's actually a link. Every element with <a> tag is a link(hyperlink)
        //! There is more than 1 spaces in the text, before text or before after text,
        //just use partialLinkText instead of linkText and don't put any space
        //This works but still we are gonna use partialLinkText
        //partialLinkText - complete match doesn't required
        //WebElement logout = driver.findElement(By.linkText("Logout"));
        WebElement logout = driver.findElement(By.partialLinkText("Logout"));


        //<a class="button secondary radius" href="/logout">
        //a - tag name , class - attribute, href - attribute, logout - text

//<a class="button secondary radius" href="/logout">
    //getAttribute() ->logout                ⬇️
                String href = logout.getAttribute("href");
                System.out.println(href);  // Printing href


        //Also we can print class name
        //<a class="button secondary radius" href="/logout"> = $0
        //<a class -> class is ==> inside the getAttribute() method ->> getAttribute("class");
        String className = logout.getAttribute("class");
        System.out.println(className);

        logout.click();
        Thread.sleep(2000);

        //Lets enter invalid credentials. Pass is SuperSecretPassword but for this
        //test we are entering sth wrong lets say "wrong" and same thing for the username also
        driver.findElement(By.name("username")).sendKeys("wrong");
        driver.findElement(By.name("password")).sendKeys("wrong");
        driver.findElement(By.id("wooden_spoon")).click();


        Thread.sleep(2000);

        WebElement errorMessage = driver.findElement(By.id("flash-messages"));
        System.out.println(errorMessage.getText());
        Thread.sleep(2000);

        driver.quit();
    }
}
