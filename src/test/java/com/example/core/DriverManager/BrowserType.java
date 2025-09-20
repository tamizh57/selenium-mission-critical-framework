package com.example.core.DriverManager;

public enum BrowserType {
    CHROME,EDGE,FIREFOX;

    public static BrowserType fromString(String browser)
    {
        return valueOf(browser.trim().toUpperCase());
    }
}
