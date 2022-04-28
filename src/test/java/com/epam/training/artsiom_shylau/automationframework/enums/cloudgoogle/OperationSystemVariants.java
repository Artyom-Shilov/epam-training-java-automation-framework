package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum OperationSystemVariants {

    FREE_DEBIAN_CENTOS_COREOS_UBUNTU_BYOL("select_option_83"),
    PAID_UBUNTU_PRO("select_option_84");

    private String optionId;

    OperationSystemVariants(String id) {
        this.optionId = id;
    }
    public String getOptionId() {
        return optionId;
    }
}
