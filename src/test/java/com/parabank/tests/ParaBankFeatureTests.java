package com.parabank.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.parabank.config.TestConfig;
import com.parabank.model.TestCustomer;
import com.parabank.pages.AccountPage;
import com.parabank.pages.ForgotLoginPage;
import com.parabank.pages.HomePage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RegistrationPage;
import org.testng.annotations.Test;

public class ParaBankFeatureTests extends BaseTest {
    @Test(description = "Account registration")
    public void customerCanRegister() {
        TestCustomer customer = TestCustomer.unique();
        new HomePage(browser).openRegistration();
        new RegistrationPage(browser).register(customer);

        assertTrue(browser.isVisible("#leftPanel"), "Successful registration should create an authenticated session");
        assertTrue(browser.isVisible("a[href*='overview.htm']"), "The customer account navigation should be available");
    }

    @Test(description = "Customer login")
    public void demoCustomerCanLogIn() {
        TestCustomer customer = demoCustomer();
        LoginPage loginPage = new LoginPage(browser);
        loginPage.login(customer);

        assertTrue(loginPage.welcomeMessage().contains("Welcome John"), "Customer welcome content should be displayed");
    }

    @Test(description = "Balance inquiry")
    public void customerCanViewAccountBalance() {
        loginDemoCustomer();
        AccountPage accountPage = new AccountPage(browser);
        accountPage.openOverview();

        assertTrue(accountPage.overview().contains("$"), "Account overview should show a balance");
    }

    @Test(description = "Account history and transaction search")
    public void customerCanViewAccountHistoryAndSearchTransactions() {
        loginDemoCustomer();
        AccountPage accountPage = new AccountPage(browser);
        accountPage.openOverview();
        accountPage.openFirstAccount();
        assertFalse(accountPage.accountActivity().isBlank(), "Account transaction history should be displayed");

        accountPage.openFindTransactions();
        assertTrue(accountPage.transactionSearchAvailable(), "Transaction search criteria should be displayed");
    }

    @Test(description = "Online account transfer")
    public void customerCanTransferBetweenAccounts() {
        loginDemoCustomer();
        AccountPage accountPage = new AccountPage(browser);
        accountPage.openTransferFunds();
        accountPage.transferFunds("1.00");

        assertTrue(accountPage.transferResult().contains("Transfer Complete"), "Transfer confirmation should be displayed");
    }

    @Test(description = "Online bill payment")
    public void customerCanPayABill() {
        loginDemoCustomer();
        AccountPage accountPage = new AccountPage(browser);
        accountPage.openBillPay();
        accountPage.payBill("1.00");

        assertTrue(accountPage.billPayResult().contains("Bill Payment Complete"), "Bill payment confirmation should be displayed");
    }

    @Test(description = "Forgot login information recovery")
    public void customerCanRecoverLoginInformation() {
        TestCustomer customer = TestCustomer.unique();
        new HomePage(browser).openRegistration();
        new RegistrationPage(browser).register(customer);
        new LoginPage(browser).logout();
        new HomePage(browser).openForgotLogin();
        new ForgotLoginPage(browser).recover(customer);

        assertTrue(new ForgotLoginPage(browser).result().contains(customer.username()),
                "Recovery result should identify the registered username");
    }

    @Test(description = "Deposit management through account opening")
    public void customerCanOpenANewCheckingAccount() {
        loginDemoCustomer();
        AccountPage accountPage = new AccountPage(browser);
        accountPage.openNewCheckingAccount();

        assertTrue(browser.isVisible("#newAccountId"), "New account confirmation should be displayed");
    }

    @Test(description = "ATM services and deposit service discovery")
    public void homepagePublishesAtmAndDepositServiceLinks() {
        HomePage homePage = new HomePage(browser);

        assertTrue(homePage.hasAtmServiceLinks(), "ATM service links should be listed on the homepage");
        assertTrue(homePage.hasDepositServiceEndpoint(), "Deposit service link should point to the ParaBank service endpoint");
    }

    @Test(description = "Service navigation")
    public void customerCanNavigateToServicesInformation() {
        new HomePage(browser).openServices();

        assertTrue(browser.currentUrl().contains("services.htm"), "Services navigation should open the services page");
        assertTrue(browser.isVisible("#rightPanel"), "Services content should be displayed");
    }

    private TestCustomer registerCustomer() {
        TestCustomer customer = TestCustomer.unique();
        new HomePage(browser).openRegistration();
        new RegistrationPage(browser).register(customer);
        return customer;
    }

    private TestCustomer loginDemoCustomer() {
        TestCustomer customer = demoCustomer();
        new LoginPage(browser).login(customer);
        return customer;
    }

    private TestCustomer demoCustomer() {
        return new TestCustomer("John", "Smith", "", "", "", "", "", "",
                TestConfig.get("demoUsername"), TestConfig.get("demoPassword"));
    }
}