package com.epam.gura;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected static Setup setup;
    protected WebDriver driver;

    @BeforeClass
    public static void preconditions(){
       
        setup = new Setup("Chrome",5);
    }

    @AfterClass
    public static void postconditions(){
        setup.getDriver().quit();
    }
}
