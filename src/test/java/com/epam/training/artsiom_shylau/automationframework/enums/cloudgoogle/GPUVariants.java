package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;

import java.util.Arrays;

public enum GPUVariants {

    TESLA_V100("NVIDIA Tesla V100", "select_option_467"),
    TESLA_P100("NVIDIA Tesla P100", "select_option_465");

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

    public static String getTypeOfGPUOptionIdByTextValue(String textValue) throws VariantSelectionException {
        return Arrays.stream(GPUVariants.values())
                .filter(v -> v.getTypeOfGPUTextValue().equalsIgnoreCase(textValue))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(textValue)))
                .getTypeOfGPUOptionId();
    }
}