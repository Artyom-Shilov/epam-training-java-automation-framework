package com.epam.training.artsiom_shylau.automationframework.service;

import com.epam.training.artsiom_shylau.automationframework.model.GPU;

public class GPUCreator {

    private static final String GPU_TYPE_KEY = "testdata.GPU.type";
    private static final String GPU_NUMBER_KEY = "testdata.GPU.number";


    private GPUCreator() {
    }

    public static GPU createGPUWithDataFromProperty() {
        return new GPU(
                TestDataReader.getTestData(GPU_TYPE_KEY),
                Integer.parseInt(TestDataReader.getTestData(GPU_NUMBER_KEY))
        );
    }
}
