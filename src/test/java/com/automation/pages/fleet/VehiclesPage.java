package com.automation.pages.fleet;

import com.automation.pages.AbstractPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Under fleet there is a module called Vehicles so we create this page class and called VehiclesPage
//For other modules like Vehicle Odometer we can do VehicleOdometerPage class
//WHY WE EXTENDED? -> PageFactory will be initialized and navigation will be available
public class VehiclesPage extends AbstractPageBase {

    @FindBy(partialLinkText = " Create Car")
    private WebElement createCar;


    public void clickToCreateCar(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(createCar)).click();  //test shouldn't worry about click
                                            //on the button. We do it here and separete this kind of actions from tests
    }

}
