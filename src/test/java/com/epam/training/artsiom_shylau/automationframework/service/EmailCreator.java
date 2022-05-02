package com.epam.training.artsiom_shylau.automationframework.service;

import com.epam.training.artsiom_shylau.automationframework.model.Email;

public class EmailCreator {

    private static final String EMAIL_ADDRESS_KEY = "testdata.email.address";

    private EmailCreator(){}

    public static Email createEmailWithDataFromProperty(){
        return new Email(TestDataReader.getTestData(EMAIL_ADDRESS_KEY));
    }
}
