package com.example.core.pages;

import com.example.core.DriverManager.ParallelDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected WebDriver getDriver()
    {
       return ParallelDriverManager.getDriver();
    }
    public BasePage()
    {
        PageFactory.initElements(getDriver(),this);
    }

}
