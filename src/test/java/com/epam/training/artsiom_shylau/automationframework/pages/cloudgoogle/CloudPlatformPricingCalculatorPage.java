package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.*;
import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.model.*;
import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CloudPlatformPricingCalculatorPage extends BasePage {

    private final Logger logger = LogManager.getRootLogger();

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

    private final String blankXpathForVariantSelectionByText =  "//div[@class = 'md-select-menu-container md-active md-clickable']//div[contains(text(), '%s')]";

    private final String blankXpathForDatacenterVariantSelectionByText =  "//div[@class = 'md-select-menu-container cpc-region-select md-active md-clickable']//div[contains(text(), '%s')]";

    public CloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    private String formXpathForVariantSelectionByText(String variantText) {
        return String.format(blankXpathForVariantSelectionByText, variantText);
    }

    public CloudPlatformPricingCalculatorPage activateComputeEngineSection() {
        waiting.waitForFrameAvailabilityAndSwitchToIt(outerFrame);
        waiting.waitForFrameAvailabilityAndSwitchToIt(myFrame);
        waiting.waitForClickableCondition(computeEngineSectionElement).click();
        logger.info("Compute engine section has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage inputNumberOfInstances(VirtualMachine machine) {
        waiting.waitForClickableCondition(numberOfInstancesInput).sendKeys(String.valueOf(machine.getNumberOfInstances()));
        logger.info("Number of instances has been entered");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseOperatingSystemByVariantId(VirtualMachine machine) throws VariantSelectionException {
        String optionId = OperatingSystemVariants.getOperationSystemOptionIdByTextValue(machine.getOperatingSystem());
        waiting.waitForClickableCondition(operationSystemSelectionElement).click();
        waiting.waitForClickableConditionById(optionId).click();
        logger.info("Operating system variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseOperatingSystemByVariantText(VirtualMachine machine) {
        waiting.waitForClickableCondition(operationSystemSelectionElement).click();
        waiting.waitForClickableCondition(formXpathForVariantSelectionByText(machine.getOperatingSystem())).click();
        logger.info("Operating system variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseMachineClassByVariantId(VirtualMachine machine) throws VariantSelectionException {
        String optionId = MachineClassVariants.getMachineClassOptionIdByTextValue(machine.getMachineClass());
        waiting.waitForClickableCondition(machineClassSelectionElement).click();
        waiting.waitForClickableConditionById(optionId).click();
        logger.info("Machine class variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseMachineClassByVariantText(VirtualMachine machine) throws VariantSelectionException {
        waiting.waitForClickableCondition(machineClassSelectionElement).click();
        waiting.waitForClickableCondition(formXpathForVariantSelectionByText(machine.getMachineClass())).click();
        logger.info("Machine class variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseInstanceTypeByVariantId(VirtualMachine machine) throws VariantSelectionException {
        MachineTypeVariants matchedVariant = MachineTypeVariants.getEnumElementByTextValue(machine.getMachineType());
        waiting.waitForClickableCondition(seriesSelectionElement).click();
        waiting.waitForClickableConditionById(matchedVariant.getSeriesOptionId()).click();
        waiting.waitForClickableCondition(machineTypeSelectionElement).click();
        waiting.waitForClickableConditionById(matchedVariant.getMachineTypeOptionId()).click();
        logger.info("Instance type variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseInstanceTypeByVariantText(VirtualMachine machine) throws VariantSelectionException {
        waiting.waitForClickableCondition(seriesSelectionElement).click();
        String machineType = machine.getMachineType();
        String machineSeries = machineType.substring(0, 2).toUpperCase();
        waiting.waitForClickableCondition(formXpathForVariantSelectionByText(machineSeries)).click();
        waiting.waitForClickableCondition(machineTypeSelectionElement).click();
        waiting.waitForClickableCondition(formXpathForVariantSelectionByText(machineType)).click();
        logger.info("Instance type variant has been chosen");
        return this;
    }


    public CloudPlatformPricingCalculatorPage addGPUByGPYTypeId(GPU graphicsProcessor) throws VariantSelectionException {
        String typeOfGpuOptionId = GPUVariants.getTypeOfGPUOptionIdByTextValue(graphicsProcessor.getTypeOfGPU());
        waiting.waitForClickableCondition(addGPUCheckbox).click();
        waiting.waitForClickableCondition(gpuTypeSelectionElement).click();
        waiting.waitForClickableConditionById(typeOfGpuOptionId).click();
        waiting.waitForClickableCondition(gpuNumberSelectionElement).click();
        waiting.waitForClickableCondition(String.format(
                blankXpathForGPUNumberVariant, graphicsProcessor.getNumberOfGPU())).click();
        logger.info("Type of GPU variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage addGPUByGPYTypeText(GPU graphicsProcessor) throws VariantSelectionException {
        waiting.waitForClickableCondition(addGPUCheckbox).click();
        waiting.waitForClickableCondition(gpuTypeSelectionElement).click();
        waiting.waitForClickableCondition(formXpathForVariantSelectionByText(graphicsProcessor.getTypeOfGPU())).click();
        waiting.waitForClickableCondition(gpuNumberSelectionElement).click();
        waiting.waitForClickableCondition(formXpathForVariantSelectionByText(String.valueOf(graphicsProcessor.getNumberOfGPU()))).click();
        logger.info("Type of GPU variant has been chosen");
        return this;
    }


    public CloudPlatformPricingCalculatorPage chooseLocalSSDById(LocalSSD localSSD) throws VariantSelectionException {
        String optionId = LocalSSDVariants.getCapacityOptionIdByTextValue(localSSD.getCapacity());
        waiting.waitForClickableCondition(localSSDSelectionElement).click();
        waiting.waitForClickableConditionById(optionId).click();
        logger.info("Local SSD variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseLocalSSDByText(LocalSSD localSSD) throws VariantSelectionException {
        String optionId = LocalSSDVariants.getCapacityOptionIdByTextValue(localSSD.getCapacity());
        waiting.waitForClickableCondition(localSSDSelectionElement).click();
        waiting.waitForClickableCondition(formXpathForVariantSelectionByText(localSSD.getCapacity())).click();
        logger.info("Local SSD variant has been chosen");
        return this;
    }
    public CloudPlatformPricingCalculatorPage chooseDatacenterLocationById(Datacenter datacenter) throws VariantSelectionException {
        String optionId = DatacenterLocationVariants.getDatacenterLocationOptionIdByTextValue(datacenter.getLocation());
        waiting.waitForClickableCondition(datacenterLocationSelectionElement).click();
        waiting.waitForClickableConditionById(optionId).click();
        logger.info("Datacenter variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseDatacenterLocationByText(Datacenter datacenter) throws VariantSelectionException {
        String optionId = DatacenterLocationVariants.getDatacenterLocationOptionIdByTextValue(datacenter.getLocation());
        waiting.waitForClickableCondition(datacenterLocationSelectionElement).click();
        waiting.waitForClickableCondition(String.format(blankXpathForDatacenterVariantSelectionByText, datacenter.getLocation())).click();
        logger.info("Datacenter variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseCommittedUsageById(UsageTerm usageTerm) throws VariantSelectionException {
        String optionId = CommittedUsageVariants.getUsageDurationOptionIdByTextValue(usageTerm.getDuration());
        waiting.waitForClickableCondition(committedUsageSelectionElement).click();
        waiting.waitForClickableConditionById(optionId).click();
        logger.info("Commitment usage variant has been chosen");
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseCommittedUsageByText(UsageTerm usageTerm) throws VariantSelectionException {
        String optionId = CommittedUsageVariants.getUsageDurationOptionIdByTextValue(usageTerm.getDuration());
        waiting.waitForClickableCondition(committedUsageSelectionElement).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", committedUsageSelectionElement);
        driver.findElement(By.xpath(formXpathForVariantSelectionByText(usageTerm.getDuration()))).click();
      //  waiting.waitForClickableCondition(formXpathForVariantSelectionByText(usageTerm.getDuration())).click();
        logger.info("Commitment usage variant has been chosen");
        return this;
    }

    public EstimatePage addToEstimate() {
        waiting.waitForClickableCondition(addToEstimateButton).click();
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
