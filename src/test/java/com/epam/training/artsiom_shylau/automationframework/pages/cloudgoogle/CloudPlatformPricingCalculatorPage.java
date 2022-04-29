package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.*;
import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.model.*;
import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

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

    private final String blankXpathForGPUNumberVariant = "//md-option[@ng-disabled = 'item.value != 0 " +
            "&& item.value < listingCtrl.minGPU' and @value = '%s']";

    public CloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public CloudPlatformPricingCalculatorPage activateComputeEngineSection() {
        waiting.waitForFrameAvailabilityAndSwitchToIt(outerFrame);
        waiting.waitForFrameAvailabilityAndSwitchToIt(myFrame);
        waiting.waitForClickableCondition(computeEngineSectionElement).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage inputNumberOfInstances(VirtualMachine machine) {
        waiting.waitForClickableCondition(numberOfInstancesInput).sendKeys(
                String.valueOf(machine.getNumberOfInstances()));
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseOperatingSystem(VirtualMachine machine) {
        waiting.waitForClickableCondition(operationSystemSelectionElement).click();
        waiting.waitForClickableConditionById(OperationSystemVariants.getOperationSystemOptionIdByTextValue(machine.getOperationSystem())).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseMachineClass(VirtualMachine machine) {
        String machineClass = machine.getMachineClass();
        waiting.waitForClickableCondition(machineClassSelectionElement).click();
        try {
            waiting.waitForClickableConditionById(
                    MachineClassVariants.valueOf(machineClass.toUpperCase()).getMachineClassOptionId()).click();
        } catch (IllegalArgumentException e) {
            throw new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(machineClass), e);
        }
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseInstanceType(VirtualMachine machine) {
        InstanceTypeVariants matchedVariant = InstanceTypeVariants.getEnumElementByTextValue(machine.getMachineType());
        waiting.waitForClickableCondition(seriesSelectionElement).click();
        waiting.waitForClickableConditionById(matchedVariant.getSeriesOptionId()).click();
        waiting.waitForClickableCondition(machineTypeSelectionElement).click();
        waiting.waitForClickableConditionById(matchedVariant.getMachineTypeOptionId()).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage addGPU(GPU graphicsProcessor) {
        waiting.waitForClickableCondition(addGPUCheckbox).click();
        waiting.waitForClickableCondition(gpuTypeSelectionElement).click();
        waiting.waitForClickableConditionById(
                GPUVariants.getTypeOfGPUOptionIdByTextValue(graphicsProcessor.getTypeOfGPU())).click();
        waiting.waitForClickableCondition(gpuNumberSelectionElement).click();
        waiting.waitForClickableCondition(String.format(
                blankXpathForGPUNumberVariant, graphicsProcessor.getNumberOfGPU())).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseLocalSSD(LocalSSD localSSD) {
        waiting.waitForClickableCondition(localSSDSelectionElement).click();
        waiting.waitForClickableConditionById(
                LocalSSDVariants.getCapacityOptionIdByTextValue(localSSD.getCapacity())).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseDatacenterLocation(Datacenter datacenter) {
        String location = datacenter.getLocation();
        waiting.waitForClickableCondition(datacenterLocationSelectionElement).click();
        try {
            waiting.waitForClickableConditionById(
                    DatacenterLocationVariants.valueOf(location.toUpperCase()).getDatacenterLocationOptionId()).click();
        } catch (IllegalArgumentException e) {
            throw new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(location));
        }
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseCommittedUsage(UsageTerm usageTerm) {
        waiting.waitForClickableCondition(committedUsageSelectionElement).click();
        waiting.waitForClickableConditionById(
                CommittedUsageVariants.getUsageDurationOptionIdByTextValue(usageTerm.getDuration())).click();
        return this;
    }


    public EstimatePage addToEstimate() {
        waiting.waitForClickableCondition(addToEstimateButton).click();
        return new EstimatePage(driver);
    }

}
