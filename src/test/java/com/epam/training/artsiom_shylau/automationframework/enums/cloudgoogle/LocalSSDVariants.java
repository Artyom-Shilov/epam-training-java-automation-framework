package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum LocalSSDVariants {

    SSD_2_375("2x375 GiB","select_option_448");

    private String textValue;
    private String optionId;

    LocalSSDVariants(String textValue, String selectOptionId) {
        this.textValue = textValue;
        this.optionId = selectOptionId;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getOptionId() {
        return optionId;
    }
}
