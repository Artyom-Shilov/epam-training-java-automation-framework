package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum CommittedUsageVariants {

    THREE_YEARS("3 Years", "select_option_120"),
    ONE_YEAR("1 Year", "select_option_119");

    private String usageDurationTextValue;
    private String usageDurationOptionId;

    CommittedUsageVariants(String value, String selectOptionId) {
        this.usageDurationTextValue = value;
        this.usageDurationOptionId = selectOptionId;
    }

    public String getUsageDurationTextValue() {
        return usageDurationTextValue;
    }

    public String getUsageDurationOptionId() {
        return usageDurationOptionId;
    }
}
