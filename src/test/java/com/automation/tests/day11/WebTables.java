package com.automation.tests.day11;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WebTables {
    private WebDriver driver;


    @BeforeMethod
    public void setup(){
        driver= DriverFactory.createDriver("chrome");
//
//        //to run browser without GUI. Makes browser invisible. It makes execution much faster
//        //set it to true to make it work
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setHeadless(true);
//        driver = new ChromeDriver(chromeOptions);

        driver.get("http://practice.cybertekschool.com/tables");
        driver.manage().window().maximize();
    }


    @Test(description = "Go to sortable data tables find column names from 1th table")
    public void getColumnNames(){
        List<String> expected = Arrays.asList("Last Name", "First Name", "Email", "Due", "Web Site", "Action");
        List<WebElement> columnNames = driver.findElements(By.xpath("//table[1]//th"));
        for (WebElement columnName : columnNames){
            System.out.println(columnName.getText());
        }

        //BrowserUtils-> class_name, getTextFromWebElements-> method_name
        Assert.assertEquals(BrowserUtils.getTextFromWebElements(columnNames),expected);
    }


    @Test(description = "How many row we have in the table 1")
    public void verifyRowCount(){
      // tbody//tr - get all rows from the table body, excluding table header
         List<WebElement> rows = driver.findElements(By.xpath("//table[1]//tbody//tr"));
      //If we'll get a size of this collection -> rows.size()
        //System.out.println(rows.size()); -> we'll see the size if we write this. Prints 4

      //rows.size()->actual  4 ->expected
        Assert.assertEquals(rows.size(), 4);
    }


    @Test(description = "Collect all websites from table 1")
    public void getSpecificColumn(){
        List<WebElement> links = driver.findElements(By.xpath("//table[1]//tbody//tr//td[5]"));
        System.out.println(BrowserUtils.getTextFromWebElements(links));

    }


    /**
     * Delete record w jsmith@gmail.com
     * Verify thar number of rows is equals to 3
     * verify that jsmith@gmail.com doesn't exist any more in the table
     * edit and delete in Action row are links
     *Table 1:
     Last Name	First Name	Email	          Due	   Web Site	              Action
     Smith	      John	   jsmith@gmail.com  $50.00  http://www.jsmith.com	 edit delete
     *
     */
    @Test(description = "delete an email address")
    public void deleteRowTest() {
        //Find the cell that has email than go to the following sibling that has text() 'delete' ->
        //or go back to parent and then find the link that has delete

        //I.WAY
        String path = "//table[1]//td[text()='jsmith@gmail.com']/..//a[text()='delete']";
        driver.findElement(By.xpath(path)).click();
        BrowserUtils.wait(2);

        //II. WAY
        // String path = "//table[1]//td[text()='fbach@yahoo.com']//following-sibling::td/a[text()='delete']";
        // driver.findElement(By.xpath(path)).click();

        int rowCount = driver.findElements(By.xpath("//table[1]//tbody//tr")).size();
        Assert.assertEquals(rowCount,3);
        BrowserUtils.wait(2);

        List<WebElement> emails = driver.findElements(By.xpath("//table[1]//td[text()='jsmith@gmail.com']"));
        Assert.assertTrue(emails.isEmpty());

    }

    /**
     * Write a function that will return column index based on column name
     * like 1 last name , 2 first name, 3 email etc
     */
    @Test
    public void getColumnIndexByName(){
        //email i ornek olarak aliyoruz
        String columnName = "Email";
        List<WebElement> columnNames = driver.findElements(By.xpath("//table[1]//th"));
        int index = 0;
        for (int i=0; i<columnNames.size(); i++){

            String actualColumnName =columnNames.get(i).getText();

          //To do String format we use %s
          //Column name deki %s will be replaced-> actualColumnName, position'daki %s" will be replaced-> i
          //Look at the print out
            //if we use printf -> it will print and format the output
            System.out.println(String.format("Column name: %s, position %s", actualColumnName, i));

            if (actualColumnName.equals(columnName)){ //column name email mi diye bakiyoruz
                index = i+1;
                break;
            }
        }
        Assert.assertEquals(index , 3); //email 3.column da
    }

    @Test
    public void getSpecificCell(){
        String expected = "http://www.jdoe.com";
        int row = 3;
        int column = 5;
        String xpath = "//table[1]//tbody//tr["+row+"]//td["+column+"]";

        //find the webelement and get the text
        WebElement cell = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(cell.getText(), expected);

    }




    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }


}
