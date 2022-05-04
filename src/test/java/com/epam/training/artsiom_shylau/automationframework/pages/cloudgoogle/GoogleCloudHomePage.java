package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends BasePage {

    private final Logger logger = LogManager.getRootLogger();

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
        logger.info("Google Cloud home page has been opened");
        return this;
    }

    public GoogleCloudHomePage hideCookieNotification(){
        waiting.waitForClickableCondition(cookieNotificationOkButton).click();
        logger.info("Cookie notification on Google Cloud has been accepted");
        return this;
    }

    public SearchPage searchForTerm(String searchTerm) {
        waiting.waitForClickableCondition(searchInputElement).sendKeys(searchTerm);
        waiting.waitForClickableCondition(allResultsButton).click();
        logger.info("Search for term" + searchTerm + " is processing");
        return new SearchPage(driver, searchTerm);
    }
}
