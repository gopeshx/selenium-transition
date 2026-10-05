package com.parabank.browser;

public final class BrowserFactory {
    private BrowserFactory() {
    }

    public static UiBrowser create() {
        return new SeleniumBrowser();
    }
}