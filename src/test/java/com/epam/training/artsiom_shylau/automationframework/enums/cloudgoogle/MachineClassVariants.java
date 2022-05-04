package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;

import java.util.Arrays;

public enum MachineClassVariants {

    REGULAR("regular","select_option_97"),
    PREEMPTIBLE("preemptible", "select_option_98");

    private String machineClassTextValue;
    private String machineClassOptionId;

    MachineClassVariants(String machineClassTextValue, String machineClassOptionId) {
        this.machineClassTextValue = machineClassTextValue;
        this.machineClassOptionId = machineClassOptionId;
    }

    public String getMachineClassOptionId() {
        return machineClassOptionId;
    }

    public String getMachineClassTextValue() {
        return machineClassTextValue;
    }

    public static String getMachineClassOptionIdByTextValue(String textValue) throws VariantSelectionException {
        return Arrays.stream(MachineClassVariants.values())
                .filter(v -> v.getMachineClassTextValue().equalsIgnoreCase(textValue))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(textValue)))
                .getMachineClassOptionId();
    }
}
