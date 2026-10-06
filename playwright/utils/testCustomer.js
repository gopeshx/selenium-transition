// Replaces com.parabank.model.TestCustomer.
const { randomUUID } = require('crypto');
const { config } = require('./testConfig');

function uniqueCustomer() {
  const unique = randomUUID().replace(/-/g, '').substring(0, 10);
  return {
    firstName: 'Taylor',
    lastName: 'Automation',
    street: '1 Test Street',
    city: 'Austin',
    state: 'TX',
    zipCode: '78701',
    phone: '5125550100',
    ssn: unique.substring(0, 9),
    username: `qa${unique}`,
    password: 'TestPass123',
  };
}

function demoCustomer() {
  return {
    firstName: 'John',
    lastName: 'Smith',
    username: config.demoUsername,
    password: config.demoPassword,
  };
}

module.exports = { uniqueCustomer, demoCustomer };
