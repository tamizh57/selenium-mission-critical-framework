package com.example.core.utils;

import com.example.core.DriverManager.ParallelDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotManager {

    public static String captureBase64()
    {
        WebDriver driver= ParallelDriverManager.getDriver();
        if(driver==null) return null;
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
