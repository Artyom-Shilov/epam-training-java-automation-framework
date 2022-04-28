package com.epam.training.artsiom_shylau.automationframework.service;

import com.epam.training.artsiom_shylau.automationframework.model.Datacenter;

public class DatacenterCreator {

    private static final String DATACENTER_LOCATION_KEY = "testdata.datacenter.location";

    private DatacenterCreator(){}

    public static Datacenter createDatacenterWithDataFromProperty() {
        return new Datacenter(TestDataReader.getTestData(DATACENTER_LOCATION_KEY));
    }
}
