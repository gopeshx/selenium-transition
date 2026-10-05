package com.parabank.browser;

import java.util.List;

public interface UiBrowser extends AutoCloseable {
    void open(String url);
    void click(String cssSelector);
    void clickLink(String linkText);
    void clickButton(String buttonText);
    void fill(String cssSelector, String value);
    void selectFirstOption(String cssSelector);
    void selectSecondOption(String cssSelector);
    void selectByValue(String cssSelector, String value);
    String text(String cssSelector);
    List<String> texts(String cssSelector);
    List<String> linkTexts(String cssSelector);
    List<String> linkTargets(String cssSelector);
    boolean isVisible(String cssSelector);
    void waitUntilVisible(String cssSelector);
    String currentUrl();
    String title();

    @Override
    void close();
}