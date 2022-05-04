package com.epam.training.artsiom_shylau.automationframework.test;

import com.epam.training.artsiom_shylau.automationframework.model.*;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.CloudPlatformPricingCalculatorPage;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.EstimatePage;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.GoogleCloudHomePage;
import com.epam.training.artsiom_shylau.automationframework.pages.yopmail.GeneratedAddressPage;
import com.epam.training.artsiom_shylau.automationframework.pages.yopmail.YopmailHomePage;
import com.epam.training.artsiom_shylau.automationframework.service.*;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;
import org.openqa.selenium.TimeoutException;
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
                .searchForTerm("Google Cloud Pricing Calculator")
                .openPageAccordingToSearchTerm();
    }

    private EstimatePage getEstimatePageAccordingToOptions()  {
        return new CloudPlatformPricingCalculatorPage(driver)
                .activateComputeEngineSection()
                .inputNumberOfInstances(virtualMachine)
                .chooseOperatingSystem(virtualMachine)
                .chooseMachineClass(virtualMachine)
                .chooseInstanceType(virtualMachine)
                .addGPU(graphicProcessor)
                .chooseLocalSSD(localSSD)
                .chooseDatacenterLocation(datacenter)
                .chooseCommittedUsage(usageTerm)
                .addToEstimate();
    }

    @Test
    public void shouldCalculatePlatformPrice() {
        openCalculatorPage();
        EstimatePage estimatePage = getEstimatePageAccordingToOptions();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(virtualMachine.getMachineClass().equalsIgnoreCase(estimatePage.readVMClass()));
        softAssert.assertTrue(virtualMachine.getMachineType().equalsIgnoreCase(estimatePage.readInstanceType()));
        softAssert.assertTrue((localSSD.getCapacity().equalsIgnoreCase(estimatePage.readLocalSSD())));
        softAssert.assertTrue(datacenter.getLocation().equalsIgnoreCase(estimatePage.readRegion()));
        softAssert.assertTrue(usageTerm.getDuration().equalsIgnoreCase(estimatePage.readCommitmentTerm()));
        softAssert.assertTrue(usageTerm.getPrice().equalsIgnoreCase(estimatePage.readTotalCommitmentCost()));
        softAssert.assertAll();
    }

    @Test
    public void shouldReceiveLetterWithCalculatedPrice() {

        final String LETTER_TOTAL_COST_SUBSTRING_START = "Total *:\n";
        final String LETTER_TOTAL_COST_SUBSTRING_END = "\n\n* The estimated";

        openCalculatorPage();
        EstimatePage estimatePage = getEstimatePageAccordingToOptions().openEmailEstimationForm();

        GeneratedAddressPage generatedAddressPage = new YopmailHomePage(driver)
                .openInNewTab()
                .generateMailAddress()
                .copyGeneratedAddress();

        estimatePage.switchToTabOfThisPage().pasteAddressAndSendEmail();

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
        Assert.assertThrows(TimeoutException.class, () -> calculatorPage.chooseOperatingSystem(virtualMachine));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenDatacenterLocationOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(TimeoutException.class, () -> calculatorPage.chooseDatacenterLocation(datacenter));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenGPUTypeOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(TimeoutException.class, () -> calculatorPage.addGPU(graphicProcessor));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenLocalSSDOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(TimeoutException.class, () -> calculatorPage.chooseLocalSSD(localSSD));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenInstanceTypeOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(TimeoutException.class, () -> calculatorPage.chooseInstanceType(virtualMachine));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenMachineClassOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(TimeoutException.class, () -> calculatorPage.chooseMachineClass(virtualMachine));
    }

    @Test
    public void shouldThrowExceptionForPriceCalculationWhenUsageDurationOptionIsUnknown() {
        openCalculatorPage();
        CloudPlatformPricingCalculatorPage calculatorPage = new CloudPlatformPricingCalculatorPage(driver);
        calculatorPage.activateComputeEngineSection();
        Assert.assertThrows(TimeoutException.class, () -> calculatorPage.chooseCommittedUsage(usageTerm));
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
    public void shouldNotBeAbleToChooseGPUWhenVirtualWhenVirtualMachineSeriesIsNotN1() {
        openCalculatorPage();
        boolean isAbleToChooseGPU = new CloudPlatformPricingCalculatorPage(driver)
                .activateComputeEngineSection()
                .chooseInstanceType(virtualMachine)
                .isPossibleToChooseGPU();

        Assert.assertFalse(isAbleToChooseGPU);
    }

    @Test
    public void shouldNotBeAbleToSendEmailWhenEnteredAddressDoesNotSuitFormat() {
        Email email = EmailCreator.createEmailWithDataFromProperty();
        openCalculatorPage();
        boolean isAbleToSendEmail = getEstimatePageAccordingToOptions()
                .openEmailEstimationForm()
                .inputAddress(email)
                .isPossibleToSendEmail();
        Assert.assertFalse(isAbleToSendEmail);
    }
}
