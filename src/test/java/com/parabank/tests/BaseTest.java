package com.parabank.tests;

import com.parabank.browser.BrowserFactory;
import com.parabank.browser.UiBrowser;
import com.parabank.config.TestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected UiBrowser browser;

    @BeforeMethod
    public void startBrowser() {
        browser = BrowserFactory.create();
        browser.open(TestConfig.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        if (browser != null) {
            browser.close();
        }
    }
}