package com.example.core.DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

    public static WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);

            case EDGE:
                EdgeOptions edgeOptions=new EdgeOptions();
//                edgeOptions.addArguments("--start-maximized");
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }

    }


}
