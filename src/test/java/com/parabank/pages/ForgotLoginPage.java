package com.parabank.pages;

import com.parabank.browser.UiBrowser;
import com.parabank.model.TestCustomer;
import com.parabank.locators.ForgotLoginPageLocators;

public class ForgotLoginPage {
    private final UiBrowser browser;

    public ForgotLoginPage(UiBrowser browser) {
        this.browser = browser;
    }

    public void recover(TestCustomer customer) {
        browser.fill(ForgotLoginPageLocators.FIRST_NAME_INPUT, customer.firstName());
        browser.fill(ForgotLoginPageLocators.LAST_NAME_INPUT, customer.lastName());
        browser.fill(ForgotLoginPageLocators.STREET_INPUT, customer.street());
        browser.fill(ForgotLoginPageLocators.CITY_INPUT, customer.city());
        browser.fill(ForgotLoginPageLocators.STATE_INPUT, customer.state());
        browser.fill(ForgotLoginPageLocators.ZIP_CODE_INPUT, customer.zipCode());
        browser.fill(ForgotLoginPageLocators.SSN_INPUT, customer.ssn());
        browser.clickButton(ForgotLoginPageLocators.RECOVER_BUTTON);
        browser.waitUntilVisible(ForgotLoginPageLocators.RIGHT_PANEL);
    }

    public String result() {
        return browser.text(ForgotLoginPageLocators.RIGHT_PANEL);
    }
}