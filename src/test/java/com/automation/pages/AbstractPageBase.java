package com.automation.pages;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
/**
 * This class will be extended by page classes. Any common webelements/locators
 * can be store here. Since navigation menu doesnt belong to particular page
 * We cannat really create a dedicated page class to store. elements from that menu
 */
public abstract class AbstractPageBase {
  protected WebDriver driver = Driver.getDriver();

  public AbstractPageBase() {  //CONSTRUCTOR
        PageFactory.initElements(driver,this);  //Page Factory has to be initialized in every Page class but since
                                                     //this class is parent class for all Page classes we r writing this just once
  }

    /**
     * @param tabName, like Dashboards, Fleet, or Customers
     * @param moduleName, like Vehicles, Vehicles Odometer and Vehicles Costs
     */
  public void navigateTo(String tabName, String moduleName){  //EX/tabName fleet moduleName is Vehicles
     //for tabName if we type Dashboard it will point Dashboard; if we type Fleet, it will point Fleet etc
     //These r our locators
      String tabNameXpath = "//span[@class='title title-level-1' and contains(text(),'"+tabName+"')]";
      String moduleXpath = "//span[@class='title title-level-2' and text()='"+moduleName+"']";
      //We have locators now we r creating Web elements
      WebElement tabElement = driver.findElement(By.xpath(tabNameXpath));
      WebElement moduleElement = driver.findElement(By.xpath(moduleXpath));

      //We need actions class cause we need to move to the tabElement
      Actions actions = new Actions(driver);
      BrowserUtils.wait(3);
      actions.moveToElement(tabElement).pause(2000).click(moduleElement).build().perform();
      BrowserUtils.wait(3);
   }

}
