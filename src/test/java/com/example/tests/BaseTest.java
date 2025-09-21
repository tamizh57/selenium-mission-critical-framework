package com.example.tests;

import com.example.core.DriverManager.BrowserType;
import com.example.core.DriverManager.DriverFactory;
import com.example.core.DriverManager.ParallelDriverManager;
import com.example.core.utils.ConfigManager;
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
        // Step 1: use parameter if provided, else fallback to config
        String browser = (browserName != null) ? browserName : ConfigManager.get("browser", "EDGE");
        BrowserType browserType = BrowserType.fromString(browser);
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
