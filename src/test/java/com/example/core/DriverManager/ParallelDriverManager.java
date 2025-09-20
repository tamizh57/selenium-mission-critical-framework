package com.example.core.DriverManager;

import org.openqa.selenium.WebDriver;

public class ParallelDriverManager {
    private  static  final ThreadLocal<WebDriver> driverPool=new ThreadLocal<>();

    public static void addDriver(WebDriver driver)
    {
        driverPool.set(driver);
    }
    public static  WebDriver getDriver()
    {
       return driverPool.get();
    }
    public static void removeDriver()
    {
        WebDriver driver= driverPool.get();
        if(driver!=null) {
            driver.quit();
            driverPool.remove();
        }

    }
}
