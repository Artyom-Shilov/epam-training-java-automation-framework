package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//input[@aria-label = 'Search']")
    private WebElement searchInputElement;

    @FindBy(xpath = "//div[@id = 'devsite-search-popout-container-id-1']//button")
    private WebElement allResultsButton;

    @FindBy(xpath = "//button[@class = 'devsite-snackbar-action']")
    private WebElement cookieNotificationOkButton;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage(){
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public GoogleCloudHomePage hideCookieNotification(){
        waiting.waitForClickableCondition(cookieNotificationOkButton).click();
        return this;
    }

    public SearchPage searchForTerm(String searchTerm) {
        waiting.waitForClickableCondition(searchInputElement).sendKeys(searchTerm);
        waiting.waitForClickableCondition(allResultsButton).click();
        return new SearchPage(driver, searchTerm);
    }
}
