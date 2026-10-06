// Replaces LoginPage.java + LoginPageLocators.java.
class LoginPage {
  /** @param {import('@playwright/test').Page} page */
  constructor(page) {
    this.page = page;
    this.usernameInput = page.locator("input[name='username']");
    this.passwordInput = page.locator("input[name='password']");
    this.logInButton = page.getByRole('button', { name: 'Log In', exact: true });
    this.logOutLink = page.getByRole('link', { name: 'Log Out', exact: true }).first();
    this.leftPanel = page.locator('#leftPanel');
    this.loginPanel = page.locator('#loginPanel');
  }

  async login(customer) {
    await this.usernameInput.fill(customer.username);
    await this.passwordInput.fill(customer.password);
    await this.logInButton.click();
    await this.leftPanel.waitFor();
  }

  async logout() {
    await this.logOutLink.click();
    await this.loginPanel.waitFor();
  }
}

module.exports = { LoginPage };
