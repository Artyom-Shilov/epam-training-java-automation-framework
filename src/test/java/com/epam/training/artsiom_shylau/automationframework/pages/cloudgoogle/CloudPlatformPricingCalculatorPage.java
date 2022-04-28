package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CloudPlatformPricingCalculatorPage extends BasePage {

    @FindBy(xpath = "//button[@class = 'devsite-snackbar-action']")
    private WebElement cookieNotificationOkButton;

    @FindBy(xpath = "//article[@id='cloud-site']//iframe")
    protected WebElement outerFrame;

    @FindBy(id = "myFrame")
    protected WebElement myFrame;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//md-tab-item/div[@title='Compute Engine']")
    private WebElement computeEngineSectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[contains(text(), 'Number of instances')]" +
            "/following-sibling::input")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'Operating System / Software']" +
            "/following-sibling::md-select")
    private WebElement operationSystemSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'Machine Class']" +
            "/following-sibling::md-select")
    private WebElement machineClassSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'Series']/following-sibling::md-select")
    private WebElement seriesSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'Machine type']/following-sibling::md-select")
    private WebElement machineTypeSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//md-checkbox[@ng-model = 'listingCtrl.computeServer.addGPUs']")
    private WebElement addGPUCheckbox;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'GPU type']/following-sibling::md-select")
    private WebElement gpuTypeSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'Number of GPUs']/following-sibling::md-select")
    private WebElement gpuNumberSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'Local SSD']" +
            "/following-sibling::md-select[@ng-model = 'listingCtrl.computeServer.ssd']")
    private WebElement localSSDSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'Datacenter location']" +
            "/following-sibling::md-select[@ng-model = 'listingCtrl.computeServer.location']")
    private WebElement datacenterLocationSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']//label[text() = 'Committed usage']" +
            "/following-sibling::md-select[@ng-model = 'listingCtrl.computeServer.cud']")
    private WebElement committedUsageSelectionElement;

    @FindBy(xpath = "//md-card-content[@id='mainForm']" +
            "//button[@ng-click = 'listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

    public CloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public CloudPlatformPricingCalculatorPage activateComputeEngineSection() {
        waiting.waitForFrameAvailabilityAndSwitchToIt(outerFrame);
        waiting.waitForFrameAvailabilityAndSwitchToIt(myFrame);
        waiting.waitForClickableCondition(computeEngineSectionElement).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage inputNumberOfInstances(int numberOfInstances) {
        waiting.waitForClickableCondition(numberOfInstancesInput).sendKeys(String.valueOf(numberOfInstances));
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseOperatingSystem(String optionId) {
        waiting.waitForClickableCondition(operationSystemSelectionElement).click();
        waiting.waitForClickableConditionById(optionId).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseMachineClass(String optionId) {
        waiting.waitForClickableCondition(machineClassSelectionElement).click();
        waiting.waitForClickableConditionById(optionId).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseInstanceType(String seriesOptionId, String machineTypeOptionId) {
        waiting.waitForClickableCondition(seriesSelectionElement).click();
        waiting.waitForClickableConditionById(seriesOptionId).click();
        waiting.waitForClickableCondition(machineTypeSelectionElement).click();
        waiting.waitForClickableConditionById(machineTypeOptionId).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage addGPU(String gpuTypeOptionId, String numberOfGPUOptionId) {
        waiting.waitForClickableCondition(addGPUCheckbox).click();
        waiting.waitForClickableCondition(gpuTypeSelectionElement).click();
        waiting.waitForClickableConditionById(gpuTypeOptionId).click();
        waiting.waitForClickableCondition(gpuNumberSelectionElement).click();
        waiting.waitForClickableConditionById(numberOfGPUOptionId).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseLocalSSD(String variantId) {
        waiting.waitForClickableCondition(localSSDSelectionElement).click();
        waiting.waitForClickableConditionById(variantId).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseDatacenterLocation(String variantId) {
        waiting.waitForClickableCondition(datacenterLocationSelectionElement).click();
        waiting.waitForClickableConditionById(variantId).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseCommittedUsage(String variantId) {
        waiting.waitForClickableCondition(committedUsageSelectionElement).click();
        waiting.waitForClickableConditionById(variantId).click();
        return this;
    }

    public EstimatePage addToEstimate() {
        waiting.waitForClickableCondition(addToEstimateButton).click();
        return new EstimatePage(driver);
    }

}
