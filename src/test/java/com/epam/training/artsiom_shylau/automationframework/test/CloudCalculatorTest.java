package com.epam.training.artsiom_shylau.automationframework.test;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.model.*;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.CloudPlatformPricingCalculatorPage;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.EstimatePage;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.GoogleCloudHomePage;
import com.epam.training.artsiom_shylau.automationframework.pages.yopmail.GeneratedAddressPage;
import com.epam.training.artsiom_shylau.automationframework.pages.yopmail.YopmailHomePage;
import com.epam.training.artsiom_shylau.automationframework.service.*;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CloudCalculatorTest extends CommonTestConditions {

    VirtualMachine virtualMachine;
    GPU graphicProcessor;
    LocalSSD localSSD;
    Datacenter datacenter;
    UsageTerm usageTerm;

    @BeforeMethod
    public void setUpModels() {
        virtualMachine = VirtualMachineCreator.createVirtualMachineWithDataFromProperty();
        graphicProcessor = GPUCreator.createGPUWithDataFromProperty();
        localSSD = LocalSSDCreator.createLocalSSDWithDataFromProperty();
        datacenter = DatacenterCreator.createDatacenterWithDataFromProperty();
        usageTerm = UsageTermCreator.createUsageTermWithDataFromProperty();
    }

    private void openCalculatorPage() {
        new GoogleCloudHomePage(driver)
                .openPage()
                //.hideCookieNotification()
                .searchForTerm("Google Cloud Platform Pricing Calculator")
                //.openPageAccordingToSearchTerm();
                .openPageAccordingToSearchTermCarefully();
    }

    private EstimatePage getEstimatePageAccordingToOptions() throws VariantSelectionException {
        return new CloudPlatformPricingCalculatorPage(driver)
                .activateComputeEngineSection()
                .inputNumberOfInstances(virtualMachine)
                .chooseOperatingSystemByVariantText(virtualMachine)
                .chooseMachineClassByVariantText(virtualMachine)
                .chooseInstanceTypeByVariantText(virtualMachine)
                .addGPUByGPYTypeText(graphicProcessor)
                .chooseLocalSSDByText(localSSD)
                .chooseDatacenterLocationByText(datacenter)
                .chooseCommittedUsageByText(usageTerm)
                .addToEstimate();
    }

    @Test
    public void shouldCalculatePlatformPrice() throws VariantSelectionException {
        openCalculatorPage();
        EstimatePage estimatePage = getEstimatePageAccordingToOptions();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(virtualMachine.getMachineClass(), estimatePage.readVMClass());
        softAssert.assertEquals(virtualMachine.getMachineType(), estimatePage.readInstanceType());
        softAssert.assertEquals(localSSD.getCapacity(), estimatePage.readLocalSSD());
        softAssert.assertEquals(datacenter.getLocation(), estimatePage.readRegion());
        softAssert.assertEquals(usageTerm.getDuration(), estimatePage.readCommitmentTerm());
        softAssert.assertEquals(usageTerm.getPrice(), estimatePage.readTotalCommitmentCost());
        softAssert.assertAll();
    }

    @Test
    public void shouldReceiveLetterWithCalculatedPrice() throws VariantSelectionException {

        final String LETTER_TOTAL_COST_SUBSTRING_START = "Total *:\n";
        final String LETTER_TOTAL_COST_SUBSTRING_END = "\n\n* The estimated";

        openCalculatorPage();
        EstimatePage estimatePage = getEstimatePageAccordingToOptions();

        GeneratedAddressPage generatedAddressPage = new YopmailHomePage(driver)
                .openInNewTab()
                .hideCookieNotification()
                .generateMailAddress()
                .copyGeneratedAddress();

        estimatePage.switchToTabOfThisPage().pasteEmailAndSendPriceLetter();

        Letter receivedLetter = generatedAddressPage
                .switchToTabOfThisPage()
                .openMailBox()
                .refreshMailBoxUntilLetterReceiving()
                .getCurrentLetter();

        Assert.assertEquals("Google Cloud Price Estimate", receivedLetter.getTheme());
        Assert.assertEquals(usageTerm.getPrice(), StringOperations.getSubstring(receivedLetter.getText(), LETTER_TOTAL_COST_SUBSTRING_START, LETTER_TOTAL_COST_SUBSTRING_END));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenOperationSystemOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(VariantSelectionException.class, () -> calculatorPage.chooseOperatingSystemByVariantId(virtualMachine));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenDatacenterLocationOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(VariantSelectionException.class, () -> calculatorPage.chooseDatacenterLocationById(datacenter));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenGPUTypeOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(VariantSelectionException.class, () -> calculatorPage.addGPUByGPYTypeText(graphicProcessor));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenLocalSSDOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(VariantSelectionException.class, () -> calculatorPage.chooseLocalSSDById(localSSD));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenInstanceTypeOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(VariantSelectionException.class, () -> calculatorPage.chooseInstanceTypeByVariantId(virtualMachine));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenMachineClassOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(VariantSelectionException.class, () -> calculatorPage.chooseMachineClassByVariantId(virtualMachine));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenUsageDurationOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(VariantSelectionException.class, () -> calculatorPage.chooseCommittedUsageById(usageTerm));
    }

    @Test
    public void shouldNotBeAbleToCalculatePriceWhenNumberOfInstancesIsNotPositive() {
        openCalculatorPage();
        boolean isAbleToCalculate = new CloudPlatformPricingCalculatorPage(driver)
                .activateComputeEngineSection()
                .inputNumberOfInstances(virtualMachine)
                .isReadyToCalculatePrice();

        Assert.assertFalse(isAbleToCalculate);
    }

    @Test
    public void shouldNotBeAbleToChooseGPUWhenVirtualWhenVirtualMachineSeriesIsNotN1() throws VariantSelectionException {
        openCalculatorPage();
        boolean isAbleToChooseGPU = new CloudPlatformPricingCalculatorPage(driver)
                .activateComputeEngineSection()
                .chooseInstanceTypeByVariantId(virtualMachine)
                .isPossibleToChooseGPU();

        Assert.assertFalse(isAbleToChooseGPU);
    }

    @Test
    public void shouldNotBeAbleToSendEmailWhenEnteredAddressDoesNotSuitFormat() throws VariantSelectionException {
        Email email = EmailCreator.createEmailWithDataFromProperty();
        openCalculatorPage();
        boolean isAbleToSendEmail = getEstimatePageAccordingToOptions()
                .openEmailEstimationForm()
                .inputEmail(email)
                .isPossibleToSendEmail();
        Assert.assertFalse(isAbleToSendEmail);
    }
}
