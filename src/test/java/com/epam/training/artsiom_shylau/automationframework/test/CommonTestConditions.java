package com.epam.training.artsiom_shylau.automationframework.test;

import com.epam.training.artsiom_shylau.automationframework.driver.DriverSingleton;
import com.epam.training.artsiom_shylau.automationframework.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonTestConditions {

    protected WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        driver = DriverSingleton.getDriver();
    }

    //@AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
