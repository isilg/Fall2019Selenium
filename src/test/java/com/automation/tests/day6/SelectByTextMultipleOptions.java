package com.automation.tests.day6;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectByTextMultipleOptions {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(2);


        //Find the Programming language dropdown and check if it supports
        //multiple selecting. isMultiple() -> It check whether the SELECT element
        //support multiple selecting options at the same time or not.
        Select languagesSelect = new Select(driver.findElement(By.name("Languages")));
        System.out.println(languagesSelect.isMultiple());
        BrowserUtils.wait(2);

        //If it is true you can select more than 1 option
        languagesSelect.selectByVisibleText("Java");
        languagesSelect.selectByVisibleText("JavaScript");
        languagesSelect.selectByVisibleText("Python");

        //Get all selected options
        List<WebElement> selectedLanguages = languagesSelect.getAllSelectedOptions();
        for (WebElement selectedLanguage : selectedLanguages){
            System.out.println(selectedLanguage.getText());
            BrowserUtils.wait(2);
        }

        //deselect Java
        languagesSelect.deselectByVisibleText("Java");
        BrowserUtils.wait(2);

        //deselect everything
        languagesSelect.deselectAll();

        BrowserUtils.wait(2);
        driver.quit();

    }
}
