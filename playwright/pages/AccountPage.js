// Replaces AccountPage.java and the account-related locator classes
// (AccountNavigation, AccountsOverview, AccountDetails, OpenAccount,
// TransferFunds, BillPay, FindTransactions).
const { config } = require('../utils/testConfig');

class AccountPage {
  /** @param {import('@playwright/test').Page} page */
  constructor(page) {
    this.page = page;

    // Left-hand navigation
    this.accountsOverviewLink = page.getByRole('link', { name: 'Accounts Overview', exact: true }).first();
    this.openNewAccountLink = page.getByRole('link', { name: 'Open New Account', exact: true }).first();
    this.transferFundsLink = page.getByRole('link', { name: 'Transfer Funds', exact: true }).first();
    this.billPayLink = page.getByRole('link', { name: 'Bill Pay', exact: true }).first();
    this.findTransactionsLink = page.getByRole('link', { name: 'Find Transactions', exact: true }).first();

    // Accounts overview / details
    this.accountTable = page.locator('#accountTable');
    this.accountCells = page.locator('#accountTable tbody tr td');
    this.firstAccountLink = page.locator('#accountTable tbody tr:first-child td a');
    this.transactionTable = page.locator('#transactionTable');

    // Open new account
    this.accountTypeSelect = page.locator('#type');
    this.openAccountFundingSelect = page.locator('#fromAccountId');
    this.openAccountButton = page.getByRole('button', { name: 'Open New Account', exact: true });
    this.newAccountId = page.locator('#newAccountId');

    // Transfer funds
    this.transferAmountInput = page.locator('#amount');
    this.transferFromSelect = page.locator('#fromAccountId');
    this.transferToSelect = page.locator('#toAccountId');
    this.transferButton = page.getByRole('button', { name: 'Transfer', exact: true });
    this.transferResult = page.locator('#showResult');

    // Bill pay
    this.payeeNameInput = page.locator("input[name='payee.name']");
    this.payeeStreetInput = page.locator("input[name='payee.address.street']");
    this.payeeCityInput = page.locator("input[name='payee.address.city']");
    this.payeeStateInput = page.locator("input[name='payee.address.state']");
    this.payeeZipCodeInput = page.locator("input[name='payee.address.zipCode']");
    this.payeePhoneInput = page.locator("input[name='payee.phoneNumber']");
    this.payeeAccountInput = page.locator("input[name='payee.accountNumber']");
    this.verifyAccountInput = page.locator("input[name='verifyAccount']");
    this.billAmountInput = page.locator("input[name='amount']");
    this.billFundingSelect = page.locator("select[name='fromAccountId']");
    this.sendPaymentButton = page.getByRole('button', { name: 'Send Payment', exact: true });
    this.billPayResult = page.locator('#billpayResult');

    // Find transactions
    this.transactionForm = page.locator('#transactionForm');
    this.findByIdButton = page.locator('#findById');
  }

  async openOverview() {
    await this.accountsOverviewLink.click();
    await this.accountTable.waitFor();
    await this.accountCells.first().waitFor();
  }

  async openFirstAccount() {
    await this.firstAccountLink.click();
    await this.transactionTable.waitFor();
  }

  async openNewCheckingAccount() {
    await this.openNewAccountLink.click();
    await this.accountTypeSelect.waitFor();
    await this.accountTypeSelect.selectOption({ index: 0 });
    await selectWhenLoaded(this.openAccountFundingSelect, config.demoFundingAccount);
    await this.openAccountButton.click();
    await this.newAccountId.waitFor();
  }

  async openTransferFunds() {
    await this.transferFundsLink.click();
    await this.transferAmountInput.waitFor();
  }

  async transferFunds(amount) {
    await this.transferAmountInput.fill(amount);
    await selectWhenLoaded(this.transferFromSelect, config.demoFundingAccount);
    await selectWhenLoaded(this.transferToSelect, config.demoDestinationAccount);
    await this.transferButton.click();
    await this.transferResult.waitFor();
  }

  async openBillPay() {
    await this.billPayLink.click();
    await this.payeeNameInput.waitFor();
  }

  async payBill(amount) {
    await this.payeeNameInput.fill('Utility Provider');
    await this.payeeStreetInput.fill('2 Utility Road');
    await this.payeeCityInput.fill('Austin');
    await this.payeeStateInput.fill('TX');
    await this.payeeZipCodeInput.fill('78701');
    await this.payeePhoneInput.fill('5125550199');
    await this.payeeAccountInput.fill('123456789');
    await this.verifyAccountInput.fill('123456789');
    await this.billAmountInput.fill(amount);
    await selectWhenLoaded(this.billFundingSelect, config.demoFundingAccount);
    await this.sendPaymentButton.click();
    await this.billPayResult.waitFor();
  }

  async openFindTransactions() {
    await this.findTransactionsLink.click();
    await this.transactionForm.waitFor();
  }
}

// ParaBank fills account <select>s via AJAX after the page loads, so wait for
// the option to exist before selecting it (same as the Selenium wait.until).
async function selectWhenLoaded(select, value) {
  await select.locator(`option[value='${value}']`).waitFor({ state: 'attached' });
  await select.selectOption(value);
}

module.exports = { AccountPage };
