package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.*;
import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.model.*;
import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

public class CloudPlatformPricingCalculatorPage extends BasePage {

    private static final String END_OF_MESSAGE_FOR_EXCEPTION = ": variant is unknown. Can not get its id for selection";

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

    public CloudPlatformPricingCalculatorPage chooseOperatingSystem(VirtualMachine machine) throws VariantSelectionException {
        String machineOperationSystem = machine.getOperationSystem();
        String variantId = Arrays.stream(OperationSystemVariants.values())
                .filter(v -> v.getOperationSystemTextValue().equals(machineOperationSystem))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(machineOperationSystem + END_OF_MESSAGE_FOR_EXCEPTION))
                .getOperationSystemOptionId();
        waiting.waitForClickableCondition(operationSystemSelectionElement).click();
        waiting.waitForClickableConditionById(variantId).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseMachineClass(VirtualMachine machine) throws VariantSelectionException {
        String machineClass = machine.getMachineClass();
        waiting.waitForClickableCondition(machineClassSelectionElement).click();
        try {
            waiting.waitForClickableConditionById(
                    MachineClassVariants.valueOf(machineClass.toUpperCase()).getMachineClassOptionId()).click();
        } catch (IllegalArgumentException e) {
            throw new VariantSelectionException(machineClass + END_OF_MESSAGE_FOR_EXCEPTION, e);
        }
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseInstanceType(VirtualMachine machine) throws VariantSelectionException {
        String machineType = machine.getMachineType();
        InstanceTypeVariants matchedVariant = Arrays.stream(InstanceTypeVariants.values())
                .filter(v -> v.getMachineTypeTextValue().equals(machineType))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(machineType + END_OF_MESSAGE_FOR_EXCEPTION));
        waiting.waitForClickableCondition(seriesSelectionElement).click();
        waiting.waitForClickableConditionById(matchedVariant.getSeriesOptionId()).click();
        waiting.waitForClickableCondition(machineTypeSelectionElement).click();
        waiting.waitForClickableConditionById(matchedVariant.getMachineTypeOptionId()).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage addGPU(GPU graphicsProcessor) throws VariantSelectionException {
        String typeOfGPU = graphicsProcessor.getTypeOfGPU();
        String typeVariantId = Arrays.stream(GPUVariants.values())
                .filter(v -> v.getTypeOfGPUTextValue().equals(typeOfGPU))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(typeOfGPU + END_OF_MESSAGE_FOR_EXCEPTION))
                .getTypeOfGPUOptionId();
        waiting.waitForClickableCondition(addGPUCheckbox).click();
        waiting.waitForClickableCondition(gpuTypeSelectionElement).click();
        waiting.waitForClickableConditionById(typeVariantId).click();
        waiting.waitForClickableCondition(gpuNumberSelectionElement).click();
        waiting.waitForClickableCondition(String.format(
                blankXpathForGPUNumberVariant, graphicsProcessor.getNumberOfGPU())).click();
        return this;
    }
    public CloudPlatformPricingCalculatorPage chooseLocalSSD(LocalSSD localSSD) throws VariantSelectionException {
        String capacity = localSSD.getCapacity();
        String variantId = Arrays.stream(LocalSSDVariants.values())
                .filter(v -> v.getCapacityTextValue().equals(capacity))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(capacity + END_OF_MESSAGE_FOR_EXCEPTION))
                .getCapacityOptionId();
        waiting.waitForClickableCondition(localSSDSelectionElement).click();
        waiting.waitForClickableConditionById(variantId).click();
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseDatacenterLocation(Datacenter datacenter) throws VariantSelectionException {
        String location = datacenter.getLocation();
        waiting.waitForClickableCondition(datacenterLocationSelectionElement).click();
        try {
            waiting.waitForClickableConditionById(
                    DatacenterLocationVariants.valueOf(location.toUpperCase()).getDatacenterLocationOptionId()).click();
        } catch (IllegalArgumentException e) {
        throw new VariantSelectionException(location + END_OF_MESSAGE_FOR_EXCEPTION, e);
    }
        return this;
    }

    public CloudPlatformPricingCalculatorPage chooseCommittedUsage(UsageTerm usageTerm) throws VariantSelectionException {
        String usageDuration = usageTerm.getDuration();
        String variantId = Arrays.stream(CommittedUsageVariants.values())
                .filter(v -> v.getUsageDurationTextValue().equals(usageDuration))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(usageDuration + END_OF_MESSAGE_FOR_EXCEPTION)).getUsageDurationOptionId();
        waiting.waitForClickableCondition(committedUsageSelectionElement).click();
        waiting.waitForClickableConditionById(variantId).click();
        return this;
    }

    public EstimatePage addToEstimate() {
        waiting.waitForClickableCondition(addToEstimateButton).click();
        return new EstimatePage(driver);
    }

}
