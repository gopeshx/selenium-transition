# ParaBank UI automation — Playwright (JavaScript)

Playwright port of the Selenium/TestNG suite in `../src/test/java`.

```bash
cd playwright
npm install
npx playwright install chromium
cp .env.example .env        # optional: override URL / demo accounts
npm test                    # headless run
npm run test:headed         # watch the browser
npm run test:ui             # interactive UI mode
npm run report              # open the HTML report
```

## Selenium → Playwright mapping

| Selenium (Java) | Playwright (JS) |
|---|---|
| `pom.xml`, `testng.xml` | `package.json`, `playwright.config.js` |
| `config.properties` + `TestConfig` | `.env` + `utils/testConfig.js` |
| `BaseTest` (`@BeforeMethod` / `@AfterMethod`) | `utils/fixtures.js` |
| `BrowserFactory`, `SeleniumBrowser`, `UiBrowser`, WebDriverManager | built in (`page` fixture) |
| `locators/*Locators.java` | locator fields inside each page object |
| `pages/*.java` | `pages/*.js` |
| `TestCustomer` record | `utils/testCustomer.js` |
| `ParaBankFeatureTests` | `tests/parabank.spec.js` |
| `ExtentTestListener` | built-in HTML reporter (`playwright-report/`) with screenshots + traces on failure |
| `WebDriverWait` / `ExpectedConditions` | auto-waiting actions and `expect(...)` web-first assertions |
