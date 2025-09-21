package com.example.tests;

import com.example.core.pages.DuckDuckGoHomePage;
import com.example.core.pages.GoogleHomePage;
import org.testng.annotations.Test;

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
