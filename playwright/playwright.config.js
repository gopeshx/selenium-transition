// @ts-check
const { defineConfig, devices } = require('@playwright/test');
const { config } = require('./utils/testConfig');

// Replaces pom.xml (surefire), testng.xml and ExtentTestListener.
module.exports = defineConfig({
  testDir: './tests',
  // testng.xml had parallel="false"; ParaBank is a shared demo server, so stay serial.
  fullyParallel: false,
  workers: 1,
  retries: process.env.CI ? 1 : 0,
  timeout: 60_000,
  expect: { timeout: 12_000 }, // explicitWaitSeconds=12
  reporter: [
    ['list'],
    ['html', { outputFolder: 'playwright-report', open: 'never' }],
  ],
  use: {
    baseURL: config.baseUrl,
    headless: config.headless,
    viewport: { width: 1440, height: 1000 }, // --window-size=1440,1000
    actionTimeout: 12_000,
    navigationTimeout: 30_000,
    screenshot: 'only-on-failure',
    trace: 'retain-on-failure',
  },
  projects: [
    { name: 'chromium', use: { ...devices['Desktop Chrome'], viewport: { width: 1440, height: 1000 } } },
  ],
});
