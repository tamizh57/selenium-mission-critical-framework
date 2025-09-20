package com.example.core.pages;

import com.example.core.customInterfaces.Navigatable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DuckDuckGoHomePage extends BasePage implements Navigatable<DuckDuckGoHomePage> {

    private final String url="https://duckduckgo.com/";
    @FindBy(id="searchbox_input")
    private  WebElement searchBox ;

    @FindBy(css=".headerNav_navLink__EEGSt")
    private WebElement hamburgerMenu;

    public void search(String text)
    {
        searchBox.sendKeys(text+"\n");
    }
    @Override
    public DuckDuckGoHomePage visit()
    {
        getDriver().get(url);
        return this;
    }

}
