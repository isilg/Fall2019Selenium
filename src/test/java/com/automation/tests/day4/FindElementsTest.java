package com.automation.tests.day4;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.sql.SQLOutput;
import java.util.List;

public class FindElementsTest {

    public static void main(String[] args)  throws Exception{

        //version("79.0") Solving problems cause by Chrome version of 80
        WebDriverManager.chromedriver().version("79.0").setup();   //setup chromedriver
        WebDriver driver = new ChromeDriver();                     //create WebDriver object

        driver.get("http://practice.cybertekschool.com");

        Thread.sleep(3000);

        //how to collect all links from the page? (get all links)
        List<WebElement> links = driver.findElements(By.tagName("a"));
        //print the text of every single link (iterate through the collection of links)
        //What's gonna be the data type we will use in the for loop->whatever is we have in the collection->WebElement
        for (WebElement link : links){            //get the text from every single link
            System.out.println(link.getText());   //print them
            System.out.println(link.getAttribute("href"));     //print attributes so you can see the links
            System.out.println();

           // //click on the link and go back
          //link.click();
          //Thread.sleep(2000);
          //driver.navigate().back();

         //****It gives StaleElementReferenceException -IT IS AN INTERVIEW QUESTION.
         // It means there is WebElement but connection is lost with a document itself.Your WebElement doesn't have a
         //connection with the page element itself. Solution: When we go back we have to refresh the page.Every time we
         //need to refresh our collection(list)[collection:link lerin hepsi]. Debug the code and you'll see you have an
         //id for each link like 1={RemoteWebElement@3719}. Everytime we refresh that number(id) will change. We can say
         //element is old, it isn't attached to the page anymore.Basically Selenium has that element but it can't use it
         //anymore so it has to find that element again.Refind the element to be able to use it(we refind all links
         //again). Selenium can't find previously located element. It happens,when you try to interact with element
         //after page refresh or navigation.

        //INTERVIEW:How do you check if element exist?->Use findElements,check the size of collection if it is 0, no element was found.

         //if it generates exception once in a while how to find the element again? -> put it into try/catch and try to find element one more time
            // try{
            //      driver.findElement(By.id("name")).click()
            // }catch(StaleElementReferenceException e){
            //    driver.findElement(By.id("name")).click()
            // }


         //***INTERVIEW: What happens if element wasn't found, in case of findElement? -  NoSuchElementException
         //              What happens if elements weren't found, in case of findElements? -nothing, you'll get empty list, no Exception
         //***INTERVIEW: How to check if element doesn't exists any more/ just doesn't exist? - check if collection(list) is empty
         //    if(driver.findElements(By.id("name")).size() == 0){
         //          element doesn't exist!
         //   }

         //RECAP: driver.findElement -> You can find 1 element
         //       driver.findElements -> Find collection(list) of elements
         //       If there is no element with this locator -> You'll get exception
        }

         //i=0 -> first link, it brings us to homepage, it is useless we don't wanna use it so started from i=1
         for (int i=1; i<links.size(); i++){
            links.get(i).click();
            driver.navigate().back();
         //refresh list. First link is A/B link on the page. Even though our WebElement(for that link) represents it,
         //now it is invalid, it is old element. Once you refresh the page or go somewhere, you lost the connection
         //between the html element and WebElement so you have to find the link again.
            links = driver.findElements(By.tagName("a"));
        }

        driver.quit();

    }
}
