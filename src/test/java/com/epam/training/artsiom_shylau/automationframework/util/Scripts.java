package com.epam.training.artsiom_shylau.automationframework.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scripts {

    private Scripts(){}

    public static void moveToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }
}