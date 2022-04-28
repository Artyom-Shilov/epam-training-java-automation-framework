package com.epam.training.artsiom_shylau.automationframework.service;

import com.epam.training.artsiom_shylau.automationframework.model.LocalSSD;

public class LocalSSDCreator {

    private static final String LOCAL_SSD_CAPACITY_KEY = "testdata.localSSD.capacity";

    private LocalSSDCreator(){}

    public static LocalSSD createLocalSSDWithDataFromProperty(){
        return new LocalSSD(TestDataReader.getTestData(LOCAL_SSD_CAPACITY_KEY));
    }
}
