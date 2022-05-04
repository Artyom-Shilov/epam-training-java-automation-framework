package com.epam.training.artsiom_shylau.automationframework.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiting {

    private WebDriverWait waitObject;

    public Waiting(WebDriver driver, long waitingTimeInSeconds) {
        waitObject = new WebDriverWait(driver, Duration.ofSeconds(waitingTimeInSeconds));
    }

    public WebElement waitForClickableCondition(WebElement element) {
       return waitObject.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForClickableCondition(String xpath) {
        return waitObject.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public WebElement waitForVisibleCondition(WebElement element) {
       return waitObject.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForFrameAvailabilityAndSwitchToIt(WebElement element) {
        waitObject.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void waitForTargetFrameDetachedSafetyAndClick(WebElement element) {
        waitObject.ignoring(WebDriverException.class).until((ExpectedCondition<Boolean>) driver -> {
            element.click();
            return true;
        });
    }

    public WebDriverWait getWebDriverWaitObject() {
        return waitObject;
    }
}
