package com.epam.training.artsiom_shylau.automationframework.enums;

public enum GPUVariants implements VariantForSelection {

    TESLA_V100("NVIDIA Tesla V100", "NVIDIA_TESLA_V100"),
    TESLA_P100("NVIDIA Tesla P100", "NVIDIA_TESLA_P100");

    private String variantText;
    private String variantValue;

    GPUVariants(String variantText, String variantValue) {
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
