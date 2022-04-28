package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum CommittedUsageVariants {

    THREE_YEARS("3 Years", "select_option_120"),
    ONE_YEAR("1 Year", "select_option_119");

    private String textValue;
    private String optionId;

    CommittedUsageVariants(String value, String selectOptionId) {
        this.textValue = value;
        this.optionId = selectOptionId;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getOptionId() {
        return optionId;
    }
}
