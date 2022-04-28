package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum DatacenterLocationVariants {

    FRANKFURT("select_option_242");

    private String datacenterLocationOptionId;

    DatacenterLocationVariants(String selectOptionId) {
        this.datacenterLocationOptionId = selectOptionId;
    }

    public String getDatacenterLocationOptionId() {
        return datacenterLocationOptionId;
    }
}
