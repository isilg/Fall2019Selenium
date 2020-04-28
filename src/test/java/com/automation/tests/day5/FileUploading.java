package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploading {
    public static void main(String[] args) {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/upload");
        BrowserUtils.wait(2);

        //Upload yapmak icin Choose File button ini seciyoruz
        WebElement upload = driver.findElement(By.id("file-upload"));

        //To upload file in selenium: use sendKeys() method and provide path to the file
        String filePath2 = "/Users/lsilavunduk/Desktop/PROJECT STEPS";
        BrowserUtils.wait(2);

        //To upload file in selenium: use sendKeys() method and provide path to the file
        //upload pom.ml file
        //String filePath = System.getProperty("user.dir")+"/pom.xml";
        //BrowserUtils.wait(3);


        //Another example
        upload.sendKeys(filePath2);
        BrowserUtils.wait(2);

        //click upload
        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.wait(2);

        driver.quit();

    }
}
