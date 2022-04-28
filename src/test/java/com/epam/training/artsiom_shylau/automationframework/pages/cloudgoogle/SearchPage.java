package com.epam.training.artsiom_shylau.automationframework.pages.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.pages.BasePage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class SearchPage extends BasePage {

    private final Logger logger = Logger.getLogger(SearchPage.class);

    private final String searchTerm;

    @FindBy(xpath = "//section[@id = 'gc-wrapper']//a[text() = 'Next']")
    private WebElement linkNext;

    private String xpathForLinkAccordingToSearchTermBlank = "//div[@id = '___gcse_0']//div[@class = 'gs-title']/a/b[text() = '%s']";


    public SearchPage(WebDriver driver, String searchTerm) {
        super(driver);
        this.searchTerm = searchTerm;
        xpathForLinkAccordingToSearchTermBlank = String.format(xpathForLinkAccordingToSearchTermBlank, searchTerm);
    }

    public void openPageAccordingToSearchTermCarefully() {
        waiting.getWebDriverWaitObject().ignoring(WebDriverException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        try {
                            if (driver.findElement(By.xpath(xpathForLinkAccordingToSearchTermBlank))
                                    .isDisplayed()) {
                                return true;
                            }
                        } catch (WebDriverException e) {
                            linkNext.click();
                            logger.log(Level.INFO, "link 'Next' has been clicked");
                        }
                        return false;
                    }
                });
        openPageAccordingToSearchTerm();
    }

    public void openPageAccordingToSearchTerm() {
        waiting.waitForClickableCondition(xpathForLinkAccordingToSearchTermBlank).click();
    }
}
