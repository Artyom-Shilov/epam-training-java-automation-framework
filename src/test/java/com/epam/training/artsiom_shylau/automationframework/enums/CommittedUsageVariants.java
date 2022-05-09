package com.epam.training.artsiom_shylau.automationframework.enums;

public enum CommittedUsageVariants implements VariantForSelection {

    THREE_YEARS("3 Years", "3"),
    ONE_YEAR("1 Year", "1");

    private String variantText;
    private String variantValue;

    CommittedUsageVariants(String variantText, String variantValue) {
        this.variantText = variantText;
        this.variantValue = variantValue;
    }

    @Override
    public String getVariantValue() {
        return variantValue;
    }

    @Override
    public String getVariantText() {
        return variantText;
    }
}
