// Replaces ParaBankFeatureTests.java.
const { test, expect } = require('../utils/fixtures');
const { uniqueCustomer, demoCustomer } = require('../utils/testCustomer');

test.describe('ParaBank customer and service features', () => {
  test('Account registration', async ({ page, homePage, registrationPage }) => {
    const customer = uniqueCustomer();
    await homePage.openRegistration();
    await registrationPage.register(customer);

    await expect(page.locator('#leftPanel'), 'Successful registration should create an authenticated session').toBeVisible();
    await expect(page.locator("a[href*='overview.htm']"), 'The customer account navigation should be available').toBeVisible();
  });

  test('Customer login', async ({ loginPage }) => {
    await loginPage.login(demoCustomer());

    await expect(loginPage.leftPanel, 'Customer welcome content should be displayed').toContainText('Welcome John');
  });

  test('Balance inquiry', async ({ loginPage, accountPage }) => {
    await loginPage.login(demoCustomer());
    await accountPage.openOverview();

    await expect(accountPage.accountTable, 'Account overview should show a balance').toContainText('$');
  });

  test('Account history and transaction search', async ({ loginPage, accountPage }) => {
    await loginPage.login(demoCustomer());
    await accountPage.openOverview();
    await accountPage.openFirstAccount();
    await expect(accountPage.transactionTable, 'Account transaction history should be displayed').not.toBeEmpty();

    await accountPage.openFindTransactions();
    await expect(accountPage.findByIdButton, 'Transaction search criteria should be displayed').toBeVisible();
  });

  test('Online account transfer', async ({ loginPage, accountPage }) => {
    await loginPage.login(demoCustomer());
    await accountPage.openTransferFunds();
    await accountPage.transferFunds('1.00');

    await expect(accountPage.transferResult, 'Transfer confirmation should be displayed').toContainText('Transfer Complete');
  });

  test('Online bill payment', async ({ loginPage, accountPage }) => {
    await loginPage.login(demoCustomer());
    await accountPage.openBillPay();
    await accountPage.payBill('1.00');

    await expect(accountPage.billPayResult, 'Bill payment confirmation should be displayed').toContainText('Bill Payment Complete');
  });

  test('Forgot login information recovery', async ({ homePage, registrationPage, loginPage, forgotLoginPage }) => {
    const customer = uniqueCustomer();
    await homePage.openRegistration();
    await registrationPage.register(customer);
    await loginPage.logout();
    await homePage.openForgotLogin();
    await forgotLoginPage.recover(customer);

    await expect(forgotLoginPage.result, 'Recovery result should identify the registered username').toContainText(customer.username);
  });

  test('Deposit management through account opening', async ({ loginPage, accountPage }) => {
    await loginPage.login(demoCustomer());
    await accountPage.openNewCheckingAccount();

    await expect(accountPage.newAccountId, 'New account confirmation should be displayed').toBeVisible();
  });

  test('ATM services and deposit service discovery', async ({ homePage }) => {
    for (const name of ['Withdraw Funds', 'Check Balances', 'Make Deposits']) {
      await expect(homePage.atmServiceLinks.filter({ hasText: name }), `ATM service link "${name}" should be listed`).not.toHaveCount(0);
    }
    await expect(homePage.atmServiceLinks.first(), 'Deposit service link should point to the ParaBank service endpoint')
      .toHaveAttribute('href', /services\/ParaBank/);
  });

  test('Service navigation', async ({ page, homePage }) => {
    await homePage.openServices();

    await expect(page, 'Services navigation should open the services page').toHaveURL(/services\.htm/);
    await expect(page.locator('#rightPanel'), 'Services content should be displayed').toBeVisible();
  });
});
