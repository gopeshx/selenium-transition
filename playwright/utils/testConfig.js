// Replaces com.parabank.config.TestConfig + config.properties.
// Values come from environment variables (or a .env file) with the same defaults.
require('dotenv').config();

const config = {
  baseUrl: process.env.BASE_URL || 'https://parabank.parasoft.com/parabank/index.htm',
  headless: (process.env.HEADLESS || 'true') !== 'false',
  demoUsername: process.env.DEMO_USERNAME || 'john',
  demoPassword: process.env.DEMO_PASSWORD || 'demo',
  demoFundingAccount: process.env.DEMO_FUNDING_ACCOUNT || '13344',
  demoDestinationAccount: process.env.DEMO_DESTINATION_ACCOUNT || '54321',
};

module.exports = { config };
