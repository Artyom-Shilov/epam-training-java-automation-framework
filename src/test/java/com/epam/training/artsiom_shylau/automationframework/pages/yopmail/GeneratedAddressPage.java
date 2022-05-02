package com.epam.training.artsiom_shylau.automationframework.pages.yopmail;

import com.epam.training.artsiom_shylau.automationframework.model.Email;
import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GeneratedAddressPage extends BasePage {

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(id = "cprnd")
    private WebElement copyAddressButton;

    @FindBy(xpath = "//button[@onclick = 'egengo();']")
    private WebElement openMailBoxButton;

    @FindBy(id = "egen")
    private WebElement generatedAddressElement;

    public GeneratedAddressPage(WebDriver driver) {
        super(driver);
    }

    public GeneratedAddressPage copyGeneratedAddress() {
        waiting.waitForTargetFrameDetachedSafetyAndClick(copyAddressButton);
        logger.info("Copy address button has been clicked");
        return this;
    }

    public MailBoxPage openMailBox() {
        waiting.waitForTargetFrameDetachedSafetyAndClick(openMailBoxButton);
        logger.info("Open mail box button has been clicked");
        return new MailBoxPage(driver);
    }

    @Override
    public GeneratedAddressPage switchToTabOfThisPage() {
        return (GeneratedAddressPage) super.switchToTabOfThisPage();
    }

    public Email getGeneratedEmail(){
        return new Email(generatedAddressElement.getText());
    }

}
