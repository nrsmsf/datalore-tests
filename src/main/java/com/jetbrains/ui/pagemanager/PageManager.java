package com.jetbrains.ui.pagemanager;

import com.jetbrains.ui.pages.DataloreLandingPage;
import org.openqa.selenium.WebDriver;

public class PageManager {
    private final DataloreLandingPage dataloreLanding;

    public PageManager(WebDriver driver) {
        this.dataloreLanding = new DataloreLandingPage(driver);
    }

    public DataloreLandingPage dataloreLanding() {
        return dataloreLanding;
    }
}

