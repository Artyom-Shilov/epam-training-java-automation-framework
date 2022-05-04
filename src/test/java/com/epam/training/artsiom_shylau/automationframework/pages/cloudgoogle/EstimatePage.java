package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.model.Email;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class EstimatePage extends CloudPlatformPricingCalculatorPage {

    private static final String TOTAL_COST_SUBSTRING_START = "USD ";
    private static final String TOTAL_COST_SUBSTRING_END = " per";
    private static final String LOCAL_SSD_SUBSTRING_END = " GiB";
    private static final String COLON_AND_SPACE = ": ";

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//md-content[@id = 'compute']//div[contains(text(), 'Region')]")
    private WebElement regionElement;

    @FindBy(xpath = "//md-content[@id = 'compute']//div[contains(text(), 'Commitment term')]")
    private WebElement commitmentTermElement;

    @FindBy(xpath = "//md-content[@id = 'compute']//div[contains(text(), 'VM class')]")
    private WebElement vmClassElement;

    @FindBy(xpath = "//md-content[@id = 'compute']//div[contains(text(), 'Instance type')]")
    private WebElement instanceTypeElement;

    @FindBy(xpath = "//md-content[@id = 'compute']//div[contains(text(), 'Local SSD')]")
    private WebElement localSSDElement;

    @FindBy(xpath = "//md-card-content[@id = 'resultBlock']//b[contains(text(), 'Total Estimated Cost')]")
    private WebElement totalEstimatedCostElement;

    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//input[@type = 'email']")
    private WebElement emailInputField;

    @FindBy(xpath = "//button[@aria-label = 'Send Email']")
    private WebElement sendEmailButton;

    public EstimatePage(WebDriver driver) {
        super(driver);
    }

    public String readVMClass() {
        return StringOperations.getSubstring(waiting.waitForVisibleCondition(vmClassElement).getText(), COLON_AND_SPACE);
    }

    public String readInstanceType() {
        return StringOperations.getSubstring(waiting.waitForVisibleCondition(instanceTypeElement).getText(), COLON_AND_SPACE, "\n");
    }

    public String readRegion() {
        return StringOperations.getSubstring(waiting.waitForVisibleCondition(regionElement).getText(), COLON_AND_SPACE);
    }

    public String readLocalSSD() {
        return StringOperations.getSubstring(waiting.waitForVisibleCondition(localSSDElement).getText(), COLON_AND_SPACE, LOCAL_SSD_SUBSTRING_END);
    }

    public String readCommitmentTerm() {
        return StringOperations.getSubstring(waiting.waitForVisibleCondition(commitmentTermElement).getText(), COLON_AND_SPACE);
    }

    public String readTotalCommitmentCost() {
        return StringOperations.getSubstring(waiting.waitForVisibleCondition(totalEstimatedCostElement).getText(), TOTAL_COST_SUBSTRING_START, TOTAL_COST_SUBSTRING_END);
    }

    public EstimatePage openEmailEstimationForm() {
        waiting.waitForClickableCondition(emailEstimateButton).click();
        logger.info("Email estimate button has been clicked");
        return this;
    }

    public EstimatePage pasteEmailAndSendPriceLetter() {
        driver.switchTo().defaultContent();
        waiting.waitForFrameAvailabilityAndSwitchToIt(outerFrame);
        waiting.waitForFrameAvailabilityAndSwitchToIt(myFrame);
        logger.info("on the email form, mail generated");
        waiting.waitForVisibleCondition(emailInputField).sendKeys(Keys.CONTROL, "v");
        logger.info("on the email form, mail input copied");
        waiting.waitForClickableCondition(sendEmailButton).click();
        logger.info("Send email button has been clicked");
        return this;
    }

    public EstimatePage inputEmail(Email email) {
        driver.switchTo().defaultContent();
        waiting.waitForFrameAvailabilityAndSwitchToIt(outerFrame);
        waiting.waitForFrameAvailabilityAndSwitchToIt(myFrame);
        waiting.waitForVisibleCondition(emailInputField).sendKeys(email.getAddress());
        return this;
    }

    @Override
    public EstimatePage switchToTabOfThisPage() {
        return (EstimatePage) super.switchToTabOfThisPage();
    }

    public boolean isPossibleToSendEmail() {
        return waiting.waitForVisibleCondition(sendEmailButton).getAttribute("disabled") == null;
    }
}
