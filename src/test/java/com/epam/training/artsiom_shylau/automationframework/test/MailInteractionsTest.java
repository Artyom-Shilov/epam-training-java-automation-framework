package com.epam.training.artsiom_shylau.automationframework.test;

import com.epam.training.artsiom_shylau.automationframework.model.Email;
import com.epam.training.artsiom_shylau.automationframework.pages.yopmail.YopmailHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailInteractionsTest extends CommonTestConditions {

    @Test
    public void shouldGenerateEmail() {
        Email generatedEmail = new YopmailHomePage(driver)
                .openPage()
                .hideCookieNotification()
                .generateMailAddress()
                .copyGeneratedAddress()
                .getGeneratedEmail();

        String address = generatedEmail.getAddress();
        Assert.assertNotNull(address);
        Assert.assertTrue(address.matches(".{5,}@\\w+\\.[a-z]{2,4}"));
    }
}
