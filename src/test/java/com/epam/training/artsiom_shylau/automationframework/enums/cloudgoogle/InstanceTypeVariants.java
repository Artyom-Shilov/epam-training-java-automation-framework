package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;

import java.util.Arrays;

public enum InstanceTypeVariants {

    N1_STANDART_8("n1-standard-8", "select_option_221", "select_option_427");

    private String machineTypeTextValue;
    private String seriesOptionId;
    private String machineTypeOptionId;

    InstanceTypeVariants(String instanceType, String seriesId, String machineTypeId) {
        this.machineTypeTextValue = instanceType;
        this.seriesOptionId = seriesId;
        this.machineTypeOptionId = machineTypeId;
    }

    public String getMachineTypeTextValue() {
        return machineTypeTextValue;
    }

    public String getSeriesOptionId() {
        return seriesOptionId;
    }

    public String getMachineTypeOptionId() {
        return machineTypeOptionId;
    }

    public static InstanceTypeVariants getEnumElementByTextValue(String textValue) {
        return Arrays.stream(InstanceTypeVariants.values())
                .filter(v -> v.getMachineTypeTextValue().equalsIgnoreCase(textValue))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(textValue)));
    }
}