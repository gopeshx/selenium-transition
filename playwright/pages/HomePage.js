// Replaces HomePage.java + HomePageLocators.java.
class HomePage {
  /** @param {import('@playwright/test').Page} page */
  constructor(page) {
    this.page = page;
    this.loginPanel = page.locator('#loginPanel');
    this.registerLink = page.getByRole('link', { name: 'Register', exact: true }).first();
    this.forgotLoginLink = page.getByRole('link', { name: 'Forgot login info?', exact: true }).first();
    this.servicesLink = page.getByRole('link', { name: 'Services', exact: true }).first();
    this.atmServiceLinks = page.locator("a[href*='services/ParaBank']");
  }

  async open() {
    await this.page.goto('');
    await this.loginPanel.waitFor();
  }

  async openRegistration() {
    await this.registerLink.click();
  }

  async openForgotLogin() {
    await this.forgotLoginLink.click();
  }

  async openServices() {
    await this.servicesLink.click();
  }
}

module.exports = { HomePage };
