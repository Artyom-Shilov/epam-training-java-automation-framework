package com.epam.training.artsiom_shylau.automationframework.pages.yopmail;

import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YopmailHomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://yopmail.com/";

    @FindBy(id = "accept")
    private WebElement acceptCookieNotificationButton;

    @FindBy(xpath = "//div[@id = 'listeliens']/a[@href = 'email-generator']")
    private WebElement generateAddressLink;

    public YopmailHomePage(WebDriver driver) {
        super(driver);
    }

    public YopmailHomePage openPage() {
        driver.navigate().to(HOME_PAGE_URL);
        return this;
    }

    public YopmailHomePage openInNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        return openPage();
    }

    public YopmailHomePage hideCookieNotification(){
        waiting.waitForClickableCondition(acceptCookieNotificationButton).click();
        waiting.getWebDriverWaitObject().until(ExpectedConditions.invisibilityOf(acceptCookieNotificationButton));
        return this;
    }

    public GeneratedAddressPage generateMailAddress() {
        waiting.waitForClickableCondition(generateAddressLink).click();
        return new GeneratedAddressPage(driver);
    }
}
