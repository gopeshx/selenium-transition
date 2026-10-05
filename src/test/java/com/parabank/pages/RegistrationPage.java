package com.parabank.pages;

import com.parabank.browser.UiBrowser;
import com.parabank.model.TestCustomer;
import com.parabank.locators.RegistrationPageLocators;

public class RegistrationPage {
    private final UiBrowser browser;

    public RegistrationPage(UiBrowser browser) {
        this.browser = browser;
    }

    public void register(TestCustomer customer) {
        fill("firstName", customer.firstName());
        fill("lastName", customer.lastName());
        fill("address.street", customer.street());
        fill("address.city", customer.city());
        fill("address.state", customer.state());
        fill("address.zipCode", customer.zipCode());
        fill("phoneNumber", customer.phone());
        fill("ssn", customer.ssn());
        fill("username", customer.username());
        fill("password", customer.password());
        browser.fill(RegistrationPageLocators.REPEATED_PASSWORD_INPUT, customer.password());
        browser.clickButton(RegistrationPageLocators.REGISTER_BUTTON);
    }

    public String confirmation() {
        browser.waitUntilVisible(RegistrationPageLocators.RIGHT_PANEL);
        return browser.text(RegistrationPageLocators.RIGHT_PANEL);
    }

    private void fill(String fieldName, String value) {
        browser.fill(RegistrationPageLocators.customerField(fieldName), value);
    }
}