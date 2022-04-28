package com.epam.training.artsiom_shylau.automationframework.pages;

import com.epam.training.artsiom_shylau.automationframework.util.Waiting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    private static final long TIME_TO_WAIT_IN_SECONDS = 10;

    protected String windowHandle;

    protected WebDriver driver;
    protected Waiting waiting;

    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("driver is null");
        }
        this.driver = driver;
        waiting = new Waiting(driver, TIME_TO_WAIT_IN_SECONDS);
        windowHandle = driver.getWindowHandle();
        PageFactory.initElements(driver, this);
    }

    public BasePage switchToTabOfPage(BasePage page) {
        driver.switchTo().window(page.windowHandle);
        return page;
    }

    public BasePage switchToTabOfThisPage(){
        driver.switchTo().window(windowHandle);
        return this;
    }
}
