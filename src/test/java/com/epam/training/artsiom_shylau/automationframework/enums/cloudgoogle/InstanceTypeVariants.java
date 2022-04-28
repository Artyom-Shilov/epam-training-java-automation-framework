package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum InstanceTypeVariants {

    N1_STANDART_8("n1-standard-8", "select_option_221", "select_option_427");

    private String textValue;
    private String seriesSelectOptionId;
    private String machineTypeSelectOptionId;

    InstanceTypeVariants(String instanceType, String seriesId, String machineTypeId) {
        this.textValue = instanceType;
        this.seriesSelectOptionId = seriesId;
        this.machineTypeSelectOptionId = machineTypeId;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getSeriesSelectOptionId() {
        return seriesSelectOptionId;
    }

    public String getMachineTypeSelectOptionId() {
        return machineTypeSelectOptionId;
    }
}