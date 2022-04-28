package com.epam.training.artsiom_shylau.automationframework.pages.yopmail;

import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GeneratedAddressPage extends BasePage {

    @FindBy(id = "cprnd")
    private WebElement copyAddressButton;

    @FindBy(xpath = "//button[@onclick = 'egengo();']")
    private WebElement openMailBoxButton;

    public GeneratedAddressPage(WebDriver driver) {
        super(driver);
    }

    public GeneratedAddressPage copyGeneratedAddress() {
        waiting.waitForTargetFrameDetachedSafetyAndClick(copyAddressButton);
        return this;
    }

    public MailBoxPage openMailBox() {
        waiting.waitForTargetFrameDetachedSafetyAndClick(openMailBoxButton);
        return new MailBoxPage(driver);
    }

    @Override
    public GeneratedAddressPage switchToTabOfThisPage() {
        return (GeneratedAddressPage) super.switchToTabOfThisPage();
    }

}
