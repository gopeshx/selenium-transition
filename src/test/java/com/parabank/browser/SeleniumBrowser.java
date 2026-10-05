package com.parabank.browser;

import com.parabank.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class SeleniumBrowser implements UiBrowser {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SeleniumBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        if (TestConfig.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,1000", "--disable-dev-shm-usage", "--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestConfig.getInt("pageLoadTimeoutSeconds")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.getInt("explicitWaitSeconds")));
    }

    @Override
    public void open(String url) {
        driver.get(url);
    }

    @Override
    public void click(String cssSelector) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector))).click();
    }

    @Override
    public void clickLink(String linkText) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
    }

    @Override
    public void clickButton(String buttonText) {
        By button = By.xpath("//button[normalize-space()=" + xpathLiteral(buttonText) + "]"
            + " | //input[@type='submit' and @value=" + xpathLiteral(buttonText) + "]"
            + " | //input[@type='button' and @value=" + xpathLiteral(buttonText) + "]");
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    @Override
    public void fill(String cssSelector, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        element.clear();
        element.sendKeys(value);
    }

    @Override
    public void selectFirstOption(String cssSelector) {
        selectByIndex(cssSelector, 0);
    }

    @Override
    public void selectSecondOption(String cssSelector) {
        selectByIndex(cssSelector, 1);
    }

    @Override
    public void selectByValue(String cssSelector, String value) {
        By selector = By.cssSelector(cssSelector);
        wait.until(driver -> driver.findElements(By.cssSelector(cssSelector + " option")).stream()
                .anyMatch(option -> value.equals(option.getAttribute("value"))));
        new Select(wait.until(ExpectedConditions.elementToBeClickable(selector))).selectByValue(value);
    }

    @Override
    public String text(String cssSelector) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))).getText().trim();
    }

    @Override
    public List<String> texts(String cssSelector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        return driver.findElements(By.cssSelector(cssSelector)).stream().map(WebElement::getText).map(String::trim).toList();
    }

    @Override
    public List<String> linkTexts(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector)).stream().map(WebElement::getText).map(String::trim).toList();
    }

    @Override
    public List<String> linkTargets(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector)).stream().map(element -> element.getAttribute("href")).toList();
    }

    @Override
    public boolean isVisible(String cssSelector) {
        List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    @Override
    public void waitUntilVisible(String cssSelector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    @Override
    public String currentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String title() {
        return driver.getTitle();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    private void selectByIndex(String cssSelector, int index) {
        By selector = By.cssSelector(cssSelector);
        wait.until(driver -> driver.findElements(By.cssSelector(cssSelector + " option")).size() > index);
        new Select(wait.until(ExpectedConditions.elementToBeClickable(selector))).selectByIndex(index);
    }

    private static String xpathLiteral(String value) {
        if (!value.contains("'")) {
            return "'" + value + "'";
        }
        if (!value.contains("\"")) {
            return "\"" + value + "\"";
        }
        return "concat('" + value.replace("'", "',\"'\",'") + "')";
    }
}