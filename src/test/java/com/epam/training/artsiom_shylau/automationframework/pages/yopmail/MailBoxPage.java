package com.epam.training.artsiom_shylau.automationframework.pages.yopmail;

import com.epam.training.artsiom_shylau.automationframework.model.Letter;
import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class MailBoxPage extends BasePage {

    private final Logger logger = LogManager.getRootLogger();

    private static final int MAIL_NUMBER_INDEX = 0;

    @FindBy(xpath = "//*[@id='mail']/pre")
    private WebElement currentLetterTextElement;

    @FindBy(xpath = "//div[@class = 'ellipsis nw b f18']")
    private WebElement currentLetterThemeElement;

    @FindBy(xpath = "//span[@class = 'ellipsis b']")
    private WebElement currentLetterSenderElement;

    @FindBy(xpath = "//button/span[text() = 'Text']")
    private WebElement showTextVersionOfLetterButton;

    @FindBy(id = "ifmail")
    private WebElement ifmailFrame;

    @FindBy(id = "refresh")
    private WebElement refreshButton;

    @FindBy(id = "nbmail")
    private WebElement numberOfMailsElement;

    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    public MailBoxPage refreshMailBoxUntilLetterReceiving() {
        waiting.getWebDriverWaitObject().ignoring(WebDriverException.class).until(
                (ExpectedCondition<Boolean>) driver -> {
                    refreshButton.click();
                    logger.info("Refresh mail box button has been clicked");
                    return Integer.parseInt(numberOfMailsElement.getText().split(" ")[MAIL_NUMBER_INDEX]) > 0;
                });
        logger.info("Letter has been received");
        return this;
    }

    public Letter getCurrentLetter() {
        waiting.waitForFrameAvailabilityAndSwitchToIt(ifmailFrame);
        waiting.waitForTargetFrameDetachedSafetyAndClick(showTextVersionOfLetterButton);
        return new Letter(
                waiting.waitForVisibleCondition(currentLetterTextElement).getText(),
                waiting.waitForVisibleCondition(currentLetterThemeElement).getText(),
                waiting.waitForVisibleCondition(currentLetterSenderElement).getText()
        );
    }
}
