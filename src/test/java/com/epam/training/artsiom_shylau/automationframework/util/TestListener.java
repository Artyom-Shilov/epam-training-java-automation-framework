package com.epam.training.artsiom_shylau.automationframework.util;

import com.epam.training.artsiom_shylau.automationframework.driver.DriverSingleton;
import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestListener implements ITestListener {

    private Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        throw new UnsupportedOperationException();
    }

    private void saveScreenshot(){
        File screenshot = ((TakesScreenshot)DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot, new File("src\\test\\resources\\screenshots\\" + getCurrentDate() + ".png"));
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

     public String getCurrentDate(){
         return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
     }
}
