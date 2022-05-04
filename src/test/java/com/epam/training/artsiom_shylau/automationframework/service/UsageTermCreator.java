package com.epam.training.artsiom_shylau.automationframework.service;

import com.epam.training.artsiom_shylau.automationframework.model.UsageTerm;

public class UsageTermCreator {

    private static final String USAGE_DURATION_KEY  = "testdata.usage.duration";
    private static final String USAGE_PRICE_KEY  = "testdata.usage.price";


    private UsageTermCreator() {}

    public static UsageTerm createUsageTermWithDataFromProperty() {
        return new UsageTerm(
                TestDataReader.getTestData(USAGE_DURATION_KEY),
                TestDataReader.getTestData(USAGE_PRICE_KEY));
    }
}
