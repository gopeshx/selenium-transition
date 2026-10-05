package com.parabank.locators;

public final class BillPayPageLocators {
    public static final String PAYEE_NAME_INPUT = "input[name='payee.name']";
    public static final String PAYEE_STREET_INPUT = "input[name='payee.address.street']";
    public static final String PAYEE_CITY_INPUT = "input[name='payee.address.city']";
    public static final String PAYEE_STATE_INPUT = "input[name='payee.address.state']";
    public static final String PAYEE_ZIP_CODE_INPUT = "input[name='payee.address.zipCode']";
    public static final String PAYEE_PHONE_INPUT = "input[name='payee.phoneNumber']";
    public static final String PAYEE_ACCOUNT_INPUT = "input[name='payee.accountNumber']";
    public static final String VERIFY_ACCOUNT_INPUT = "input[name='verifyAccount']";
    public static final String AMOUNT_INPUT = "input[name='amount']";
    public static final String FUNDING_ACCOUNT_SELECT = "select[name='fromAccountId']";
    public static final String SEND_PAYMENT_BUTTON = "Send Payment";
    public static final String RESULT = "#billpayResult";

    private BillPayPageLocators() {
    }
}