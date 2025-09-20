package com.example.core.pages;

import com.example.core.customInterfaces.Navigatable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage extends BasePage implements Navigatable<GoogleHomePage> {

    private final String url="https://www.google.com";
    @FindBy(name="q1")
    private WebElement q;


    @Override
    public GoogleHomePage visit()
    {
        getDriver().get(url);
        return this;
    }
    public void search(String text)
    {
        q.sendKeys(text+"\n");
    }
}
