package com.parabank.pages;

import com.parabank.browser.UiBrowser;
import com.parabank.config.TestConfig;
import com.parabank.locators.AccountDetailsPageLocators;
import com.parabank.locators.AccountNavigationLocators;
import com.parabank.locators.AccountsOverviewPageLocators;
import com.parabank.locators.BillPayPageLocators;
import com.parabank.locators.FindTransactionsPageLocators;
import com.parabank.locators.OpenAccountPageLocators;
import com.parabank.locators.TransferFundsPageLocators;

public class AccountPage {
    private final UiBrowser browser;

    public AccountPage(UiBrowser browser) {
        this.browser = browser;
    }

    public void openOverview() {
        browser.clickLink(AccountNavigationLocators.ACCOUNTS_OVERVIEW_LINK);
        browser.waitUntilVisible(AccountsOverviewPageLocators.ACCOUNT_TABLE);
        browser.waitUntilVisible(AccountsOverviewPageLocators.ACCOUNT_ROWS);
    }

    public String overview() {
        return browser.text(AccountsOverviewPageLocators.ACCOUNT_TABLE);
    }

    public void openFirstAccount() {
        browser.click(AccountsOverviewPageLocators.FIRST_ACCOUNT_LINK);
        browser.waitUntilVisible(AccountDetailsPageLocators.TRANSACTION_TABLE);
    }

    public String accountActivity() {
        return browser.text(AccountDetailsPageLocators.TRANSACTION_TABLE);
    }

    public void openNewCheckingAccount() {
        browser.clickLink(AccountNavigationLocators.OPEN_NEW_ACCOUNT_LINK);
        browser.waitUntilVisible(OpenAccountPageLocators.ACCOUNT_TYPE_SELECT);
        browser.selectFirstOption(OpenAccountPageLocators.ACCOUNT_TYPE_SELECT);
        browser.selectByValue(OpenAccountPageLocators.FUNDING_ACCOUNT_SELECT, TestConfig.get("demoFundingAccount"));
        browser.clickButton(OpenAccountPageLocators.OPEN_ACCOUNT_BUTTON);
        browser.waitUntilVisible(OpenAccountPageLocators.NEW_ACCOUNT_ID);
    }

    public void openTransferFunds() {
        browser.clickLink(AccountNavigationLocators.TRANSFER_FUNDS_LINK);
        browser.waitUntilVisible(TransferFundsPageLocators.AMOUNT_INPUT);
    }

    public void transferFunds(String amount) {
        browser.fill(TransferFundsPageLocators.AMOUNT_INPUT, amount);
        browser.selectByValue(TransferFundsPageLocators.SOURCE_ACCOUNT_SELECT, TestConfig.get("demoFundingAccount"));
        browser.selectByValue(TransferFundsPageLocators.DESTINATION_ACCOUNT_SELECT, TestConfig.get("demoDestinationAccount"));
        browser.clickButton(TransferFundsPageLocators.TRANSFER_BUTTON);
        browser.waitUntilVisible(TransferFundsPageLocators.RESULT);
    }

    public String transferResult() {
        return browser.text(TransferFundsPageLocators.RESULT);
    }

    public void openBillPay() {
        browser.clickLink(AccountNavigationLocators.BILL_PAY_LINK);
        browser.waitUntilVisible(BillPayPageLocators.PAYEE_NAME_INPUT);
    }

    public void payBill(String amount) {
        browser.fill(BillPayPageLocators.PAYEE_NAME_INPUT, "Utility Provider");
        browser.fill(BillPayPageLocators.PAYEE_STREET_INPUT, "2 Utility Road");
        browser.fill(BillPayPageLocators.PAYEE_CITY_INPUT, "Austin");
        browser.fill(BillPayPageLocators.PAYEE_STATE_INPUT, "TX");
        browser.fill(BillPayPageLocators.PAYEE_ZIP_CODE_INPUT, "78701");
        browser.fill(BillPayPageLocators.PAYEE_PHONE_INPUT, "5125550199");
        browser.fill(BillPayPageLocators.PAYEE_ACCOUNT_INPUT, "123456789");
        browser.fill(BillPayPageLocators.VERIFY_ACCOUNT_INPUT, "123456789");
        browser.fill(BillPayPageLocators.AMOUNT_INPUT, amount);
        browser.selectByValue(BillPayPageLocators.FUNDING_ACCOUNT_SELECT, TestConfig.get("demoFundingAccount"));
        browser.clickButton(BillPayPageLocators.SEND_PAYMENT_BUTTON);
        browser.waitUntilVisible(BillPayPageLocators.RESULT);
    }

    public String billPayResult() {
        return browser.text(BillPayPageLocators.RESULT);
    }

    public void openFindTransactions() {
        browser.clickLink(AccountNavigationLocators.FIND_TRANSACTIONS_LINK);
        browser.waitUntilVisible(FindTransactionsPageLocators.FORM);
    }

    public void searchTransactions() {
        browser.selectFirstOption(FindTransactionsPageLocators.ACCOUNT_SELECT);
    }

    public boolean transactionResultsVisible() {
        return browser.isVisible(AccountDetailsPageLocators.TRANSACTION_TABLE);
    }

    public boolean transactionSearchAvailable() {
        return browser.isVisible(FindTransactionsPageLocators.FIND_BY_ID_BUTTON);
    }
}