package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NestedFrame {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/nested_frames");
        BrowserUtils.wait(2);

        //we have 2 frames: top frame and bottom frame. Choose top frame.
        //On the top there are 3 frames, choose the middle one and bring the content

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        WebElement content = driver.findElement(By.id("content"));
        System.out.println( content.getText());


     //Switch to the right frame and bring the content.
     //Direk middle dan right'a atlayamayiz, once geri top frame e gidip sonra
     //right a gecmek zorundayiz. Parent frame kullan -> driver.switchTo().parentFrame();
     //There is just <body> RIGHT</body> for that frame so:

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");
        WebElement contentRight = driver.findElement(By.tagName("body"));
        System.out.println(contentRight.getText());


        //Go to bottom frame, bring the content in it
        // driver.switchTo().parentFrame();  -->> NoSuchFrameException
        //If you switch to top to bottom frame
        //.defaultContent(); --> means exit from frame, first exit then go back to the bottom one
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        WebElement contentBottom = driver.findElement(By.tagName("body"));
        System.out.println(contentBottom.getText());

        driver.quit();


    }
}
