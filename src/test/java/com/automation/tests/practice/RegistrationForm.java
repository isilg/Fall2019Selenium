package com.automation.tests.practice;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationForm {
    private String URL ="http://practice.cybertekschool.com/registration_form";
    private WebDriver driver;
    private By firstNameBy = By.name("firstname");
    private By lastNameBy = By.name("lastname");
    private By usernameBy = By.name("username");
    private By emailBy = By.name("email");
    private By passwordBy = By.name("password");
    private By phoneBy = By.name("phone");
    private By femaleBy = By.cssSelector("input[value='female']");
    private By maleBy = By.cssSelector("input[value='male]");
    private By dobBy = By.name("birthday");
    private By departmentBy = By.name("department");
    private By jobTitleBy = By.name("job_title");
    //preceding: going up
    private By cplusplusBy = By.xpath("//label[text()='C++']/preceding-sibling::input");
    private By javaBy = By.xpath("//label[text()='Java']/preceding-sibling::input");
    //private By javaBy = By.id("inlineCheckbox2");
    private By javascriptBy = By.xpath("//label[text()='JavaScript']/preceding-sibling::input");
    //private By javascriptBy = By.id("inlineCheckbox3");
    private By signUpBy = By.id("wooden_spoon");


    @Test
    public void test1(){
        driver.findElement(firstNameBy).sendKeys("Patrick");
        BrowserUtils.wait(2);
        driver.findElement(lastNameBy).sendKeys("White");
        BrowserUtils.wait(2);
        driver.findElement(usernameBy).sendKeys("testuser");
        BrowserUtils.wait(2);
        driver.findElement(emailBy).sendKeys("test@email.com");
        BrowserUtils.wait(2);
        driver.findElement(passwordBy).sendKeys("12345678");
        BrowserUtils.wait(2);
        driver.findElement(phoneBy).sendKeys("234-123-1231");
        BrowserUtils.wait(2);

        driver.findElement(femaleBy).click();
        BrowserUtils.wait(2);

        driver.findElement(dobBy).sendKeys("01/02/1940");
        BrowserUtils.wait(2);

        Select departmentSelect = new Select(driver.findElement(departmentBy));
        departmentSelect.selectByVisibleText("Department of Engineering");
        BrowserUtils.wait(2);

        Select jobTitleSelect = new Select(driver.findElement(jobTitleBy));
        jobTitleSelect.selectByVisibleText("SDET");
        BrowserUtils.wait(2);

        driver.findElement(javaBy).click();
        BrowserUtils.wait(2);

        driver.findElement(signUpBy).click();
        BrowserUtils.wait(2);


        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.tagName("p")).getText();

        Assert.assertEquals(actual,expected);


    }

    @Test
    public void verifyFirstNameLengthTest(){
        //name must be more than 2 character. We type 1 character check if error message will print
        driver.findElement(firstNameBy).sendKeys("a");
        BrowserUtils.wait(2);
        WebElement warningMessage = driver.findElement(By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']"));
        Assert.assertTrue(warningMessage.isDisplayed());
    }

    @Test
    public void verifyAlphabeticLettersOnlyTest(){
        //we enter numbers instead of letters to firstname
        driver.findElement(firstNameBy).sendKeys("123");
        BrowserUtils.wait(2);
        WebElement warningMessage= driver.findElement(By.xpath("//small[text()='first name can only consist of alphabetical letters']"));
        Assert.assertTrue(warningMessage.isDisplayed());
    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }


}
