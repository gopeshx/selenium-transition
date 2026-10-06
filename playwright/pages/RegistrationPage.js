// Replaces RegistrationPage.java + RegistrationPageLocators.java.
class RegistrationPage {
  /** @param {import('@playwright/test').Page} page */
  constructor(page) {
    this.page = page;
    this.repeatedPasswordInput = page.locator("input[name='repeatedPassword']");
    this.registerButton = page.getByRole('button', { name: 'Register', exact: true });
    this.rightPanel = page.locator('#rightPanel');
  }

  customerField(fieldName) {
    return this.page.locator(`input[name='customer.${fieldName}']`);
  }

  async register(customer) {
    await this.customerField('firstName').fill(customer.firstName);
    await this.customerField('lastName').fill(customer.lastName);
    await this.customerField('address.street').fill(customer.street);
    await this.customerField('address.city').fill(customer.city);
    await this.customerField('address.state').fill(customer.state);
    await this.customerField('address.zipCode').fill(customer.zipCode);
    await this.customerField('phoneNumber').fill(customer.phone);
    await this.customerField('ssn').fill(customer.ssn);
    await this.customerField('username').fill(customer.username);
    await this.customerField('password').fill(customer.password);
    await this.repeatedPasswordInput.fill(customer.password);
    await this.registerButton.click();
  }
}

module.exports = { RegistrationPage };
