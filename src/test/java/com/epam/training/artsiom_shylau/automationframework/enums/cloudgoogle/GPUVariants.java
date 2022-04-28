package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum GPUVariants {

    //"select_option_471"

    TESLA_V100("TESLA V100", "select_option_467");

    private String typeOfGPUTextValue;
    private String typeOfGPUOptionId;

    GPUVariants(String typeOfGPUTextValue, String typeOfGPUSelectOptionId) {
        this.typeOfGPUTextValue = typeOfGPUTextValue;
        this.typeOfGPUOptionId = typeOfGPUSelectOptionId;
    }

    public String getTypeOfGPUTextValue() {
        return typeOfGPUTextValue;
    }

    public String getTypeOfGPUOptionId() {
        return typeOfGPUOptionId;
    }
}