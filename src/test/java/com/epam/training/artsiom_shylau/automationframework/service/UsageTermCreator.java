package com.epam.training.artsiom_shylau.automationframework.service;

import com.epam.training.artsiom_shylau.automationframework.model.UsageTerm;
import com.epam.training.artsiom_shylau.automationframework.model.VirtualMachine;

public class UsageTermCreator {

    private static final String USAGE_DURATION_KEY  = "testdata.usage.duration";

    private UsageTermCreator() {}

    public static UsageTerm createUsageTermWithDataFromProperty() {
        return new UsageTerm(TestDataReader.getTestData(USAGE_DURATION_KEY));
    }
}
