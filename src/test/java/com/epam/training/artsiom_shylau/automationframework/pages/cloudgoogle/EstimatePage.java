package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class EstimatePage extends CloudPlatformPricingCalculatorPage {

    private static final String TOTAL_COST_SUBSTRING_START = "USD ";
    private static final String TOTAL_COST_SUBSTRING_END = " per";
    private static final String COLON_AND_SPACE = ": ";

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

    @FindBy(id = "input_538")
    private WebElement emailInputField;

    @FindBy(xpath = "//md-dialog[@id = 'dialogContent_544']//button[@aria-label = 'Send Email']")
    private WebElement sendEmailButton;

    public EstimatePage(WebDriver driver) {
        super(driver);
    }

    private String getTextAfterColon(WebElement element) {
        String elementText = element.getText();
        return elementText.substring(elementText.indexOf(COLON_AND_SPACE) + COLON_AND_SPACE.length());
    }

    private String getTextAfterColonInFirstLine(WebElement element) {
        String elementText = element.getText();
        return elementText.substring(
                elementText.indexOf(COLON_AND_SPACE) + COLON_AND_SPACE.length(),
                elementText.indexOf("\n"));
    }

    public String readVMClass() {
        return getTextAfterColon(waiting.waitForVisibleCondition(vmClassElement));
    }

    public String readInstanceType() {
        return getTextAfterColonInFirstLine(waiting.waitForVisibleCondition(instanceTypeElement));
    }

    public String readRegion() {
        return getTextAfterColon(waiting.waitForVisibleCondition(regionElement));
    }

    public String readLocalSSD() {
        return getTextAfterColonInFirstLine(waiting.waitForVisibleCondition(localSSDElement));
    }

    public String readCommitmentTerm() {
        return getTextAfterColon(waiting.waitForVisibleCondition(commitmentTermElement));
    }

    public String readTotalCommitmentCost() {
        String totalCostText = waiting.waitForVisibleCondition(totalEstimatedCostElement)
                .getText();
        return totalCostText.substring(
                totalCostText.indexOf(TOTAL_COST_SUBSTRING_START) + TOTAL_COST_SUBSTRING_START.length(),
                totalCostText.indexOf(TOTAL_COST_SUBSTRING_END));
    }

    public EstimatePage openEmailEstimationForm() {
        waiting.waitForClickableCondition(emailEstimateButton).click();
        return this;
    }

    public EstimatePage pasteEmailAndSend() {
        waiting.waitForFrameAvailabilityAndSwitchToIt(outerFrame);
        waiting.waitForFrameAvailabilityAndSwitchToIt(myFrame);
        waiting.waitForVisibleCondition(emailInputField).sendKeys(Keys.CONTROL, "v");
        waiting.waitForClickableCondition(sendEmailButton).click();
        return this;
    }

    @Override
    public EstimatePage switchToTabOfThisPage() {
        return (EstimatePage) super.switchToTabOfThisPage();
    }
}
