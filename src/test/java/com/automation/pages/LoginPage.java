package com.automation.pages;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "prependedInput")
    private WebElement userName;
    //II. way
    //public WebElement username = Driver.getDriver().findElement(By.id("prependedInput"));

    @FindBy(id = "prependedInput2")
    private WebElement password;

    @FindBy(id = "_submit")
    private WebElement login;

    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotPassword;

    @FindBy(css = "[class='alert alert-error']")
    private WebElement warningMessage;



    public LoginPage() {  //CREATING CONSTRUCTOR. THIS PAGE HAS LOCATORS SO OUR TEST NEED TO CONNECT TO THIS CLASS SO THEY WILL CREATE OBJECTS OF THIS CLASS AND CONSTRUCTOR WILL NEED TO BE CALLED
        //we use this method to connect our webdriver, page class, and page factory
        //PageFactory - comes from Selen we dont need to create it. It helps
        //to find elements easier. It is used to use @FindBy annotations
        //Driver.getDriver() - What is your webDriver ,  this - what is the page class
        PageFactory.initElements(Driver.getDriver(), this);  ///this line is always same & always in constructor
    }

    /**
     * Method to login, version #
     * USE THIS ONE TO LOGIN AS A SPECIFIC USER, FOR EXAMPLE LOGIN AS A DRIVER.
     * @param usernameValue
     * @param passwordValue
     */
    public void login(String usernameValue, String passwordValue) {   //creating login method. It takes
                                         //credentials from u. 2nd login method takes credentials from properties file
        userName.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);
        BrowserUtils.wait(3);
    }
    //II.WAY by using this kywr
//    public void login(String username, String password){
//    this.username.sendKeys(username);  //our parameter has the same name as the instance parameter(line 11) so we need to use this kywr or give another name to parameter
   //}


    /**
     * 2nd version of login method
     * WHICH ONE TO USE? -BASED ON UR TEST SCENARIO
     * USE THIS ONE TO LOGIN AS A DEFAULT USER, IT YOU DONT CARE HOW TO LOGIN AS WHOM USE THIS ONE
     * IF U HAVE A CREDENTIAL LIKE LOGIN AS MANAGER OR LOGIN AS DRIVER THEN USE 1TH LOGIN METHOD
     * Go to configuration.properties(in target file) u'll see store_manager & password in the Credential
     * store_manager=...&password=... -> these credentials and getProperty() parameters have to match
     */
    public void login(){   //overloading login method. Takes credentials from properties file
        //inside the configuration.properties we have "store_manager" and "password" credentials
        //we need to give the same credential name, don't do type
        userName.sendKeys(ConfigurationReader.getProperty("store_manager"));
        password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
    }


    public String getWarningMessageText(){
        return warningMessage.getText();
    }

}
