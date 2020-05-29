package com.automation.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserUtils {

//    Type ->Thread.sleep(1000*sec); ->highlight(choose it) -> option + command + T
    public static void wait(int sec){
        try {
            Thread.sleep(1000*sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //create a method that will get text from every web element from collection and return the text back
    //type: List<String> -> it returns a group of text of elements so it will be a List(more than 1 element) this is why we did List<String>
    //name: getTextFromWebElements
    //parameter: a list of webElements (List<WebElement> elements) -> think like int x .Instead of int it is a list of webelements
    public static List<String> getTextFromWebElements(List<WebElement> elements){
        List<String> textValues = new ArrayList<>();
        for (WebElement element : elements){
            if(!element.getText().isEmpty()) {
                textValues.add(element.getText());
            }
        }
        return textValues;
    }


    /**
     * waits for backgrounds processes on the browser to complete
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds){
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }


    /**
     * Clicks on an element using JavaScript
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }


    /**
     * Scroll to element using JavaScript
     * @param element
     */
    public static void scrollTo(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * This method will take a screenshot and will return the location of screenshot.
     * Then we can attach the screenshot to the report. Call method save the screenshot as a png file thats it
     * path - location of screenshot
     * test-output - in this folder we r gonna store screenshots
     * @param name - screenshot name
     * @return - path to the screenshot
     */
    public static String getScreenshot(String name){
        name= new Date().toString()+"_"+name;      //If u wanna get a fancy name w date time. This line is optional
                                                  //LocalDateTime.now()+"_"+name;  -> dateTime first, name later
                                                  // name = name +"_"+ LocalDateTime.now(); ->other version of DateTime
        String path = System.getProperty("user.dir")+"/test-output/screenshots/"+name+".png"; //Where we store screenshot, location of screenshot.
                                                             //user.dir -> is java property. It returns location of proj. then it will create test-output
                                                             //around pom.xml file. In that folder it will be created screenshots file

     //Windows ve MAC in her ikisi icinde calisiyor                                                        //in that folder u'll find the name w .png extension. It will be screenshot.
//        String path = "";
//
//        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
//            path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
//        } else {
//            path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
//        }




        System.out.println("Screenshot is here: "+ path);
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver(); //Since our reference type is a Webdriver we cannot see
                                                                 //methods from Takescreenshot interface so we cast driver to take a screenshot

        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);  //getScreenshotAs() take screenshot of web browser, and save it as a file
                                                                         //after casting we can get getScreenshotAs().
                                                                        //This line returns file. It is screenshot itself
        File destination = new File(path); //where screenshots place. We can save it anywhere in our laptop no restriction

        try {
            FileUtils.copyFile(source, destination);   //FileUtils is a class that works with files
        } catch (IOException e) {
            e.printStackTrace();
        }


        return path;
    }


}
