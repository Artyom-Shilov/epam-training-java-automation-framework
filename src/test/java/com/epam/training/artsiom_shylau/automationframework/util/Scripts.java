package com.epam.training.artsiom_shylau.automationframework.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scripts {

    private Scripts(){}

    public static String getInputFieldText(WebDriver driver, WebElement element){
       return (String)((JavascriptExecutor) driver).executeScript("return arguments[0].value", element);
    }
}
