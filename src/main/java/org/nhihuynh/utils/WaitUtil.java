package org.nhihuynh.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WaitUtil {

    private final WebDriverWait wait;

    // You can have a default timeout
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    /**
     * Constructor using a default timeout.
     * @param driver The AppiumDriver instance.
     */
    public WaitUtil(AppiumDriver driver) {
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }


    /**
     * Waits for an element (from PageFactory) to be clickable.
     * @param element The WebElement proxy from @FindBy.
     * @return The WebElement once it is clickable.
     */
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for an element (from PageFactory) to be visible.
     * @param element The WebElement proxy from @FindBy.
     * @return The WebElement once it is visible.
     */
    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for an element (from PageFactory) to be invisible.
     * @param element The WebElement proxy from @FindBy.
     * @return True if the element becomes invisible, false otherwise.
     */
    public Boolean waitForElementToBeInvisible(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public Boolean waitUntilContextViewTobeVisible(Set<String> contexts, String contextName) {
       return wait.until(d -> {
            System.out.println("Available contexts: " + contexts);
            return contexts.stream().anyMatch(c -> c.contains("WEBVIEW"));
        });
    }
}
