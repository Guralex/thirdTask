package com.epam.gura.helpers;

import com.epam.gura.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;



public class WaiterHelper {
  

    public static void waitVisibilityOf(Setup setup, WebElement webElement){
        getWaitDriver(setup).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitVisibilityOfAll(Setup setup, List<WebElement> webElements){
        getWaitDriver(setup).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    private static WebDriverWait getWaitDriver(Setup setup) {
        return new WebDriverWait(setup.getDriver(),5);
    }
}
