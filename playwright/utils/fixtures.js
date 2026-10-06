// Replaces BaseTest.java + BrowserFactory/SeleniumBrowser/UiBrowser.
// Playwright creates a fresh browser context per test and closes it afterwards,
// so there is no driver to set up or quit by hand.
const base = require('@playwright/test');
const { HomePage } = require('../pages/HomePage');
const { LoginPage } = require('../pages/LoginPage');
const { RegistrationPage } = require('../pages/RegistrationPage');
const { ForgotLoginPage } = require('../pages/ForgotLoginPage');
const { AccountPage } = require('../pages/AccountPage');

const test = base.test.extend({
  // @BeforeMethod: every test starts on the ParaBank home page.
  page: async ({ page }, use) => {
    await page.goto('');
    await use(page);
  },
  homePage: async ({ page }, use) => use(new HomePage(page)),
  loginPage: async ({ page }, use) => use(new LoginPage(page)),
  registrationPage: async ({ page }, use) => use(new RegistrationPage(page)),
  forgotLoginPage: async ({ page }, use) => use(new ForgotLoginPage(page)),
  accountPage: async ({ page }, use) => use(new AccountPage(page)),
});

module.exports = { test, expect: base.expect };
