package com.epam.training.artsiom_shylau.automationframework.pages.yopmail;

import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle.SearchPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static org.apache.log4j.Level.INFO;

public class MailBoxPage extends BasePage {

    private Logger logger = Logger.getLogger(SearchPage.class);

    private static final String TOTAL_COST_SUBSTRING_START = "Total *:\n";
    private static final String TOTAL_COST_SUBSTRING_END = "\n\n* The estimated";

    @FindBy(xpath = "//*[@id='mail']/pre")
    private WebElement currentLetterTextElement;

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

    public MailBoxPage refreshMailBox() {
       waiting.getWebDriverWaitObject().ignoring(WebDriverException.class).until(
               (ExpectedCondition<Boolean>) driver -> {
           refreshButton.click();
           logger.log(INFO, "refresh button has been clicked");
           return Integer.parseInt(
                   numberOfMailsElement.getText().split(" ")[0]) > 0;
       });
        return this;
    }

    public String readCurrentLetter(){
        waiting.waitForFrameAvailabilityAndSwitchToIt(ifmailFrame);
        waiting.waitForTargetFrameDetachedSafetyAndClick(showTextVersionOfLetterButton);
        return waiting.waitForVisibleCondition(currentLetterTextElement).getText();
    }

    public String readTotalCost() {
        String text = readCurrentLetter();
        return text.substring(
                text.indexOf(TOTAL_COST_SUBSTRING_START) + TOTAL_COST_SUBSTRING_START.length(),
                text.indexOf(TOTAL_COST_SUBSTRING_END));
    }
}
