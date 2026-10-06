// Replaces ForgotLoginPage.java + ForgotLoginPageLocators.java.
class ForgotLoginPage {
  /** @param {import('@playwright/test').Page} page */
  constructor(page) {
    this.page = page;
    this.firstNameInput = page.locator("input[name='firstName']");
    this.lastNameInput = page.locator("input[name='lastName']");
    this.streetInput = page.locator("input[name='address.street']");
    this.cityInput = page.locator("input[name='address.city']");
    this.stateInput = page.locator("input[name='address.state']");
    this.zipCodeInput = page.locator("input[name='address.zipCode']");
    this.ssnInput = page.locator("input[name='ssn']");
    this.recoverButton = page.getByRole('button', { name: 'Find My Login Info', exact: true });
    this.result = page.locator('#rightPanel');
  }

  async recover(customer) {
    await this.firstNameInput.fill(customer.firstName);
    await this.lastNameInput.fill(customer.lastName);
    await this.streetInput.fill(customer.street);
    await this.cityInput.fill(customer.city);
    await this.stateInput.fill(customer.state);
    await this.zipCodeInput.fill(customer.zipCode);
    await this.ssnInput.fill(customer.ssn);
    await this.recoverButton.click();
  }
}

module.exports = { ForgotLoginPage };
