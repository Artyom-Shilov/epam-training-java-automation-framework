package com.epam.training.artsiom_shylau.automationframework.tests;

import com.epam.training.artsiom_shylau.automationframework.driver.DriverSingleton;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.EstimatePage;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.GoogleCloudHomePage;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.CloudPlatformPricingCalculatorPage;
import com.epam.training.artsiom_shylau.automationframework.pages.yopmail.GeneratedAddressPage;
import com.epam.training.artsiom_shylau.automationframework.pages.yopmail.MailBoxPage;
import com.epam.training.artsiom_shylau.automationframework.pages.yopmail.YopmailHomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import static com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.CommittedUsageVariants.ONE_YEAR;
//import static com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.DatacenterLocationVariants.EUROPE_WEST_3;
//import static com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.GPUVariants.TESLA_V100_1;
import static com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.InstanceTypeVariants.N1_STANDART_8;
//import static com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.LocalSSDVariants.SSD_2_375;
//import static com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.OperationSystemVariants.FREE_DEBIAN_CENTOS_COREOS_UBUNTU_BYOL;

public class GoogleCloudAndYopmailTest  {

    private WebDriver driver;

    @BeforeAll
    public static void setOptions(){
        System.setProperty("browser", "chrome");
    }

    @BeforeEach
    public void setUp(){
        driver = DriverSingleton.getDriver();
    }

    @Test
    public void hurtMePlentyScenario(){

        new GoogleCloudHomePage(driver)
                .openPage()
                .hideCookieNotification()
                .searchForTerm("Google Cloud Platform Pricing Calculator")
                .openPageAccordingToSearchTermCarefully();

        EstimatePage estimatePage = new CloudPlatformPricingCalculatorPage(driver)
                .activateComputeEngineSection()
               // .inputNumberOfInstances(4)
              //  .chooseOperatingSystem(FREE_DEBIAN_CENTOS_COREOS_UBUNTU_BYOL.getOperationSystemOptionId())
              //  .chooseMachineClass(REGULAR.getOptionId())
             //   .chooseInstanceType(
                 //       N1_STANDART_8.getSeriesSelectOptionId(),
                //        N1_STANDART_8.getMachineTypeSelectOptionId())
               // .addGPU(TESLA_V100_1.getTypeOfGPUSelectOptionId(), TESLA_V100_1.getNumberOfGpuSelectOptionId())
              //  .chooseLocalSSD(SSD_2_375.getOptionId())
              //  .chooseDatacenterLocation(EUROPE_WEST_3.getOptionId())
             //   .chooseCommittedUsage(ONE_YEAR.getUsageDurationOptionId())
                .addToEstimate();

        Assertions.assertAll(
              //  () -> Assertions.assertEquals(REGULAR.getTextValue(), estimatePage.readVMClass()),
                () -> Assertions.assertEquals(N1_STANDART_8.getMachineTypeTextValue(), estimatePage.readInstanceType()),
              //  () -> Assertions.assertEquals(EUROPE_WEST_3.getTextValue(), estimatePage.readRegion()),
              //  () -> Assertions.assertEquals(SSD_2_375.getTextValue(), estimatePage.readLocalSSD()),
                () -> Assertions.assertEquals(ONE_YEAR.getUsageDurationTextValue(), estimatePage.readCommitmentTerm()),
                () -> Assertions.assertEquals("1,082.77", estimatePage.readTotalCommitmentCost()));
    }

    @Test
    public void hardcoreScenario() {

        new GoogleCloudHomePage(driver)
                .openPage()
                .hideCookieNotification()
                .searchForTerm("Google Cloud Platform Pricing Calculator")
                .openPageAccordingToSearchTermCarefully();

        EstimatePage estimatePage = new CloudPlatformPricingCalculatorPage(driver)
                .activateComputeEngineSection()
             //   .inputNumberOfInstances(4)
            //    .chooseOperatingSystem(FREE_DEBIAN_CENTOS_COREOS_UBUNTU_BYOL.getOperationSystemOptionId())
            //    .chooseMachineClass(REGULAR.getOptionId())
            //    .chooseInstanceType(
             //           N1_STANDART_8.getSeriesSelectOptionId(),
             //           N1_STANDART_8.getMachineTypeSelectOptionId())
               // .addGPU(TESLA_V100_1.getTypeOfGPUSelectOptionId(), TESLA_V100_1.getNumberOfGpuSelectOptionId())
              //  .chooseLocalSSD(SSD_2_375.getOptionId())
               // .chooseDatacenterLocation(EUROPE_WEST_3.getOptionId())
               // .chooseCommittedUsage(ONE_YEAR.getUsageDurationOptionId())
                .addToEstimate()
                .openEmailEstimationForm();

        String expectedTotalCommitmentCost = estimatePage.readTotalCommitmentCost();

        GeneratedAddressPage generatedAddressPage = new YopmailHomePage(driver)
                .openInNewTab()
                .hideCookieNotification()
                .generateMailAddress()
                .copyGeneratedAddress();

        estimatePage.switchToTabOfThisPage().pasteEmailAndSend();

        MailBoxPage mailBoxPage = generatedAddressPage.switchToTabOfThisPage().openMailBox().refreshMailBox();

        Assertions.assertEquals(expectedTotalCommitmentCost, mailBoxPage.readTotalCost());
    }

    @AfterEach
    public void tearDown(){
        DriverSingleton.closeDriver();
    }
}
