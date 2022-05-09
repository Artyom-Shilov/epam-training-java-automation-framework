package com.epam.training.artsiom_shylau.automationframework.enums;

public enum DatacenterVariants implements VariantForSelection {

    FRANKFURT("Frankfurt", "europe-west3"),
    LOS_ANGELES("Los Angeles", "us-west2");

    private String variantText;
    private String variantValue;

    DatacenterVariants(String variantText, String variantValue) {
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
