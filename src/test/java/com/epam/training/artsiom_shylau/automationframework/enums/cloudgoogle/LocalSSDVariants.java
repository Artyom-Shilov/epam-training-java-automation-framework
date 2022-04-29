package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;

import java.util.Arrays;

public enum LocalSSDVariants {

    SSD_2_375_GB("2x375","select_option_448");

    private String capacityTextValue;
    private String capacityOptionId;

    LocalSSDVariants(String textValue, String selectOptionId) {
        this.capacityTextValue = textValue;
        this.capacityOptionId = selectOptionId;
    }

    public String getCapacityTextValue() {
        return capacityTextValue;
    }

    public String getCapacityOptionId() {
        return capacityOptionId;
    }

    public static String getCapacityOptionIdByTextValue(String textValue) {
        return Arrays.stream(LocalSSDVariants.values())
                .filter(v -> v.getCapacityTextValue().equalsIgnoreCase(textValue))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(textValue)))
                .getCapacityOptionId();
    }
}
