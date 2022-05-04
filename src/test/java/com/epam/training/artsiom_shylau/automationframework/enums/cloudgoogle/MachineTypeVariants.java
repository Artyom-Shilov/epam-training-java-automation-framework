package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;

import java.util.Arrays;

public enum MachineTypeVariants {

    N1_STANDART_8("n1-standard-8", "select_option_222", "select_option_431"),
    N1_HIGHMEM_4("n1-highmem-4", "select_option_222", "select_option_437"),
    N2_STANDART_16("n2-standard-16", "select_option_223", "select_option_465");

    private String machineTypeTextValue;
    private String seriesOptionId;
    private String machineTypeOptionId;

    MachineTypeVariants(String instanceType, String seriesId, String machineTypeId) {
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

    public static MachineTypeVariants getEnumElementByTextValue(String textValue) throws VariantSelectionException {
        return Arrays.stream(MachineTypeVariants.values())
                .filter(v -> v.getMachineTypeTextValue().equalsIgnoreCase(textValue))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(textValue)));
    }
}
