package com.parabank.pages;

import com.parabank.browser.UiBrowser;
import com.parabank.config.TestConfig;
import com.parabank.locators.HomePageLocators;

public class HomePage {
    protected final UiBrowser browser;

    public HomePage(UiBrowser browser) {
        this.browser = browser;
    }

    public void open() {
        browser.open(TestConfig.get("baseUrl"));
        browser.waitUntilVisible(HomePageLocators.LOGIN_PANEL);
    }

    public void openRegistration() {
        browser.clickLink(HomePageLocators.REGISTER_LINK);
    }

    public void openForgotLogin() {
        browser.clickLink(HomePageLocators.FORGOT_LOGIN_LINK);
    }

    public void openServices() {
        browser.clickLink(HomePageLocators.SERVICES_LINK);
    }

    public boolean hasAtmServiceLinks() {
        return browser.linkTexts(HomePageLocators.ATM_SERVICE_LINKS).containsAll(
                java.util.List.of("Withdraw Funds", "Check Balances", "Make Deposits"));
    }

    public boolean hasDepositServiceEndpoint() {
        return browser.linkTargets(HomePageLocators.ATM_SERVICE_LINKS).stream()
                .anyMatch(target -> target != null && target.contains("services/ParaBank"));
    }
}