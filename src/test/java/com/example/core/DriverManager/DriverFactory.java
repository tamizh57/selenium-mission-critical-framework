package com.example.core.DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DriverFactory {
    public static WebDriver createDriver(BrowserType browserType) {
        boolean isRemote = Boolean.parseBoolean(System.getProperty("remote", "false"));
        String gridUrl = System.getProperty("grid.url", "http://localhost:4444/wd/hub");
        try {
            if (isRemote) {
                return createRemoteDriver(browserType, gridUrl);
            } else {
                return createLocalDriver(browserType);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create driver: " + e.getMessage(), e);
        }

    }

    private static WebDriver createLocalDriver(BrowserType browserType) {
        return switch (browserType) {
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                yield new ChromeDriver(chromeOptions);
            }
            case EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                yield new EdgeDriver(edgeOptions);
            }
            case FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                yield new FirefoxDriver(firefoxOptions);
            }
        };

    }

    private static WebDriver createRemoteDriver(BrowserType browserType, String gridUrl) throws MalformedURLException, URISyntaxException {
        URL url=new URI(gridUrl).toURL();
        return switch (browserType) {
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                yield new RemoteWebDriver(url, chromeOptions);
            }
            case EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                yield new RemoteWebDriver(url, edgeOptions);
            }
            case FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                yield new RemoteWebDriver(url, firefoxOptions);
            }
        };
    }


}
