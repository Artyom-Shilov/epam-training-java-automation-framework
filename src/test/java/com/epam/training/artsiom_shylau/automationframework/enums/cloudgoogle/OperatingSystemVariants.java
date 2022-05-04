package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;

import java.util.Arrays;

public enum OperatingSystemVariants {

    FREE("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)","select_option_84"),
    PAID_UBUNTU_PRO("Paid: Ubuntu Pro", "select_option_85");

    private String operatingSystemTextValue;
    private String operatingSystemOptionId;

    OperatingSystemVariants(String operationSystemTextValue, String operationSystemOptionId) {
        this.operatingSystemTextValue = operationSystemTextValue;
        this.operatingSystemOptionId = operationSystemOptionId;
    }

    public String getOperatingSystemTextValue() {
        return operatingSystemTextValue;
    }

    public String getOperatingSystemOptionId() {
        return operatingSystemOptionId;
    }

    public static String getOperationSystemOptionIdByTextValue(String textValue) throws VariantSelectionException {
        return Arrays.stream(OperatingSystemVariants.values())
                .filter(v -> v.getOperatingSystemTextValue().equalsIgnoreCase(textValue))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(textValue)))
                .getOperatingSystemOptionId();
    }
}
