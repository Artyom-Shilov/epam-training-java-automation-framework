package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;

import java.util.Arrays;

public enum OperationSystemVariants {

    FREE("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)","select_option_83"),
    PAID_UBUNTU_PRO("Paid: Ubuntu Pro", "select_option_84");

    private String operationSystemTextValue;
    private String operationSystemOptionId;

    OperationSystemVariants(String operationSystemTextValue, String operationSystemOptionId) {
        this.operationSystemTextValue = operationSystemTextValue;
        this.operationSystemOptionId = operationSystemOptionId;
    }

    public String getOperationSystemTextValue() {
        return operationSystemTextValue;
    }

    public String getOperationSystemOptionId() {
        return operationSystemOptionId;
    }

    public static String getOperationSystemOptionIdByTextValue(String textValue) throws VariantSelectionException {
        return Arrays.stream(OperationSystemVariants.values())
                .filter(v -> v.getOperationSystemTextValue().equalsIgnoreCase(textValue))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(textValue)))
                .getOperationSystemOptionId();
    }
}
