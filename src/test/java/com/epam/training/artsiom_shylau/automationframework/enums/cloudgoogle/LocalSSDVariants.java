package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

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
}
