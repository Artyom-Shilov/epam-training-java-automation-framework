package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum DatacenterLocationVariants {

    EUROPE_WEST_3("Frankfurt", "select_option_242");

    private String textValue;
    private String optionId;

    DatacenterLocationVariants(String location, String selectOptionId) {
        this.textValue = location;
        this.optionId = selectOptionId;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getOptionId() {
        return optionId;
    }
}
