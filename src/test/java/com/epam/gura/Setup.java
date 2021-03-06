package com.epam.gura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class Setup {
    private WebDriver driver;
    private String driverName;

    public Setup(String driverName, int seconds) {
        this.driverName = driverName;
        switch (driverName) {
            case "Firefox": {
                driver = new FirefoxDriver();
                break;
            }
            case "Chrome": {
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            }
            case "Opera": {
                System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
                driver = new OperaDriver();
                break;
            }
            case "IE": {
                System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            }
            default: {
                throw new IllegalArgumentException("Incorrect browser name " + driverName);
            }
        }
       
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public String getDriverName() {
        return driverName;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
