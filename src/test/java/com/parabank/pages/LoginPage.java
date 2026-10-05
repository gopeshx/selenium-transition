package com.parabank.pages;

import com.parabank.browser.UiBrowser;
import com.parabank.model.TestCustomer;
import com.parabank.locators.LoginPageLocators;

public class LoginPage {
    private final UiBrowser browser;

    public LoginPage(UiBrowser browser) {
        this.browser = browser;
    }

    public void login(TestCustomer customer) {
        browser.fill(LoginPageLocators.USERNAME_INPUT, customer.username());
        browser.fill(LoginPageLocators.PASSWORD_INPUT, customer.password());
        browser.clickButton(LoginPageLocators.LOG_IN_BUTTON);
        browser.waitUntilVisible(LoginPageLocators.LEFT_PANEL);
    }

    public void logout() {
        browser.clickLink(LoginPageLocators.LOG_OUT_LINK);
        browser.waitUntilVisible(LoginPageLocators.LOGIN_PANEL);
    }

    public String welcomeMessage() {
        return browser.text(LoginPageLocators.LEFT_PANEL);
    }
}