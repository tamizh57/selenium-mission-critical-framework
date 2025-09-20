package com.example.tests;

import com.example.core.DriverManager.ParallelDriverManager;
import com.example.core.pages.DuckDuckGoHomePage;
import com.example.core.pages.GoogleHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ExampleTest extends BaseTest {

    @Test
    public void openGoogle() {

            new GoogleHomePage()
                    .visit()
                    .search("apple");
//            assertTrue(driver.getTitle().toLowerCase().contains("google"));

    }

    @Test
    public void openDuckDuck() {
        try {
            new DuckDuckGoHomePage()
                    .visit()
                    .search("orange");
//               assertTrue(driver.getTitle().toLowerCase().contains("duckduckgo"));

        } catch (Exception e) {

        }
    }

}
