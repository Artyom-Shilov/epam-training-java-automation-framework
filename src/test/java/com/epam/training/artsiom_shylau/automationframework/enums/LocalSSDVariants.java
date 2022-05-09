package com.epam.training.artsiom_shylau.automationframework.enums;

public enum LocalSSDVariants implements VariantForSelection {

    SSD_2_375_GB("2x375","2"),
    SSD_4_375_GB("4x375", "4");

    private String variantText;
    private String variantValue;

    LocalSSDVariants(String variantText, String variantValue) {
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
