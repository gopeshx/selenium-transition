package com.parabank.locators;

public final class RegistrationPageLocators {
    public static final String CUSTOMER_FIELD_PREFIX = "input[name='customer.";
    public static final String CUSTOMER_FIELD_SUFFIX = "']";
    public static final String REPEATED_PASSWORD_INPUT = "input[name='repeatedPassword']";
    public static final String REGISTER_BUTTON = "Register";
    public static final String RIGHT_PANEL = "#rightPanel";

    private RegistrationPageLocators() {
    }

    public static String customerField(String fieldName) {
        return CUSTOMER_FIELD_PREFIX + fieldName + CUSTOMER_FIELD_SUFFIX;
    }
}