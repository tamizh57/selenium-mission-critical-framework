package com.example.core.pages;

import com.example.core.DriverManager.ParallelDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class BasePage {

    protected WebDriver getDriver()
    {
       return ParallelDriverManager.getDriver();
    }
    public BasePage()
    {
        PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(),10),this);
    }

}
