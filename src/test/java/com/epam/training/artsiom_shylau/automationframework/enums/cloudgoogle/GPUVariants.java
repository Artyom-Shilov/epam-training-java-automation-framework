package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum GPUVariants {

    TESLA_V100_1("select_option_467", "select_option_471");

    private String typeOfGPUSelectOptionId;
    private String numberOfGpuSelectOptionId;

    GPUVariants(String typeOfGPUId, String numberOfGpuId) {
        this.typeOfGPUSelectOptionId = typeOfGPUId;
        this.numberOfGpuSelectOptionId = numberOfGpuId;
    }

    public String getTypeOfGPUSelectOptionId() {
        return typeOfGPUSelectOptionId;
    }

    public String getNumberOfGpuSelectOptionId() {
        return numberOfGpuSelectOptionId;
    }
}