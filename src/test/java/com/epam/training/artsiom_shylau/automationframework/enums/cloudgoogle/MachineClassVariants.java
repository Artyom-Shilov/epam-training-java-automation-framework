package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum MachineClassVariants {

    REGULAR("regular", "select_option_96"),
    PREEMPTIBLE("preemptible", "select_option_97");

    private String textValue;
    private String optionId;

    MachineClassVariants(String value, String id) {
        this.textValue = value;
        this.optionId = id;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getOptionId() {
        return optionId;
    }
}
