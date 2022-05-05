package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.model.*;
import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CloudPricingCalculatorPage extends BasePage {

    private final Logger logger = LogManager.getRootLogger();

    private static final int MACHINE_SERIES_SUBSTRING_END_INDEX = 2;

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

    private final String blankXpathForGPUNumberVariant = "//md-option[@ng-disabled = 'item.value != 0 " +
            "&& item.value < listingCtrl.minGPU' and @value = '%s']";

    private static final String BLANK_XPATH_FOR_VARIANT_SELECTION_BY_TEXT =
            "//div[@class = 'md-select-menu-container md-active md-clickable']//div[contains(text(), '%s')]";

    private static final String BLANK_XPATH_FOR_DATACENTER_VARIANT_SELECTION_BY_TEXT =
            "//div[@class = 'md-select-menu-container cpc-region-select md-active md-clickable']" +
                    "//div[contains(text(), '%s')]";

    public CloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    private String formXpathForVariantSelectionByText(String variantText) {
        return String.format(BLANK_XPATH_FOR_VARIANT_SELECTION_BY_TEXT, variantText);
    }

    public CloudPricingCalculatorPage activateComputeEngineSection() {
        waiting.waitForFrameAvailabilityAndSwitchToIt(outerFrame);
        waiting.waitForFrameAvailabilityAndSwitchToIt(myFrame);
        waiting.waitForClickableCondition(computeEngineSectionElement).click();
        logger.info("Compute engine section has been chosen");
        return this;
    }

    public CloudPricingCalculatorPage inputNumberOfInstances(VirtualMachine machine) {
        waiting.waitForClickableCondition(numberOfInstancesInput)
                .sendKeys(String.valueOf(machine.getNumberOfInstances()));
        logger.info("Number of instances has been entered");
        return this;
    }

    public CloudPricingCalculatorPage chooseOperatingSystem(VirtualMachine machine) {
        waiting.waitForClickableConditionMoveToElementAndClick(driver, operationSystemSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver,
                formXpathForVariantSelectionByText(machine.getOperatingSystem()));
        logger.info("Operating system variant has been chosen");
        return this;
    }

    public CloudPricingCalculatorPage chooseMachineClass(VirtualMachine machine) {
        waiting.waitForClickableConditionMoveToElementAndClick(driver, machineClassSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver,
                formXpathForVariantSelectionByText(machine.getMachineClass()));
        logger.info("Machine class variant has been chosen");
        return this;
    }

    public CloudPricingCalculatorPage chooseInstanceType(VirtualMachine machine) {
        String machineType = machine.getMachineType();
        String machineSeries = machineType.substring(0, MACHINE_SERIES_SUBSTRING_END_INDEX).toUpperCase();
        waiting.waitForClickableConditionMoveToElementAndClick(driver, seriesSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver, formXpathForVariantSelectionByText(machineSeries));
        waiting.waitForClickableConditionMoveToElementAndClick(driver, machineTypeSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver, formXpathForVariantSelectionByText(machineType));
        logger.info("Instance type variant has been chosen");
        return this;
    }

    public CloudPricingCalculatorPage addGPU(GPU graphicsProcessor) {
        waiting.waitForClickableConditionMoveToElementAndClick(driver, addGPUCheckbox);
        waiting.waitForClickableConditionMoveToElementAndClick(driver, gpuTypeSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver,
                formXpathForVariantSelectionByText(graphicsProcessor.getTypeOfGPU()));
        waiting.waitForClickableConditionMoveToElementAndClick(driver, gpuNumberSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver,
                formXpathForVariantSelectionByText(String.valueOf(graphicsProcessor.getNumberOfGPU())));
        logger.info("Type of GPU variant has been chosen");
        return this;
    }

    public CloudPricingCalculatorPage chooseLocalSSD(LocalSSD localSSD) {
        waiting.waitForClickableConditionMoveToElementAndClick(driver, localSSDSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver,
                formXpathForVariantSelectionByText(localSSD.getCapacity()));
        logger.info("Local SSD variant has been chosen");
        return this;
    }


    public CloudPricingCalculatorPage chooseDatacenterLocation(Datacenter datacenter) {
        waiting.waitForClickableConditionMoveToElementAndClick(driver, datacenterLocationSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver,
                String.format(BLANK_XPATH_FOR_DATACENTER_VARIANT_SELECTION_BY_TEXT, datacenter.getLocation()));
        logger.info("Datacenter variant has been chosen");
        return this;
    }


    public CloudPricingCalculatorPage chooseCommittedUsage(UsageTerm usageTerm) {
        waiting.waitForClickableConditionMoveToElementAndClick(driver, committedUsageSelectionElement);
        waiting.waitForClickableConditionMoveToElementAndClick(driver,
                formXpathForVariantSelectionByText(usageTerm.getDuration()));
        logger.info("Commitment usage variant has been chosen");
        return this;
    }

    public EstimatePage addToEstimate() {
        waiting.waitForClickableConditionMoveToElementAndClick(driver, addToEstimateButton);
        logger.info("Estimate button has been clicked");
        return new EstimatePage(driver);
    }

    public boolean isReadyToCalculatePrice(){
       return waiting.waitForVisibleCondition(addToEstimateButton).getAttribute("disabled") == null;
    }

    public boolean isPossibleToChooseGPU() {
        return !Boolean.parseBoolean(waiting.waitForVisibleCondition(addGPUCheckbox).getAttribute("aria-disabled"));
    }
}
