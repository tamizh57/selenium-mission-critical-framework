package com.example.tests;

import com.example.core.DriverManager.BrowserType;
import com.example.core.DriverManager.DriverFactory;
import com.example.core.DriverManager.ParallelDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun=true)
    @Parameters({"browser"})
    public void setUp(@Optional("EDGE") String browserName)
    {
        BrowserType browserType = BrowserType.fromString(browserName);
        WebDriver driver=DriverFactory.createDriver(browserType);
        ParallelDriverManager.addDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown()
    {
        ParallelDriverManager.removeDriver();
    }
}
