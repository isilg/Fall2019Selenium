package com.automation.utilities;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

//    Type ->Thread.sleep(1000*sec); ->higlight(choose it) -> option + command + T

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
            textValues.add(element.getText());
        }
        return textValues;
    }

}
