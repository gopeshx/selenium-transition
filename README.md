# ParaBank UI Automation

Java 17+, Maven, Selenium, TestNG, and ExtentReports automation for the public ParaBank demo at `https://parabank.parasoft.com/`.

## Run

Prerequisites: JDK 17 or newer, Maven, and Google Chrome. WebDriverManager resolves the matching ChromeDriver.

```powershell
mvn clean test
```

Tests run headlessly by default. Override browser settings or the application URL with Maven system properties:

```powershell
mvn test -Dheadless=false -Dbrowser=chrome
mvn test -DbaseUrl=https://parabank.parasoft.com/parabank/index.htm
```

Test results are available in `target/surefire-reports/`. The Extent report is written to `target/extent-reports/ParaBank.html`.

## Coverage

- Customer registration and login
- Account overview and balance inquiry
- Account activity and transaction search
- Opening a checking account and transferring funds between accounts
- Online bill payment
- Forgot-login recovery
- Service-page navigation
- ATM and deposit service-link discovery

ParaBank exposes its ATM actions (including “Make Deposits”) as service-description links on the homepage, not as authenticated ATM transaction screens. That test verifies the published service links only. Opening a checking account is covered as the available account-management action; the demo does not expose a separate deposit-management UI.

Registration and recovery tests create unique demo customers. Account workflows use the site's seeded `john` demo account and configured funding/destination accounts; bill payment, transfers, and account opening change this shared test data. Override those values in `src/test/resources/config.properties` if the demo fixture changes. Since this is a shared public demo, runs can be affected by concurrent use, remote availability, throttling, or site data resets; do not use real personal information.

## Playwright Migration Boundary

Test flows call page objects, page objects use the `UiBrowser` interface, and Selenium-specific setup/interactions live in `SeleniumBrowser`. A Playwright migration can add another `UiBrowser` implementation and switch `BrowserFactory` without rewriting the feature tests or page-object workflows. Keep browser actions in that adapter and avoid importing Selenium types outside it.

Page-specific selector constants live in `com.parabank.locators`, with separate classes for registration, login, recovery, account overview/details, opening accounts, transfers, bill pay, and transaction search. Page objects own workflows, and the suite is sequential because it writes to the public demo service.