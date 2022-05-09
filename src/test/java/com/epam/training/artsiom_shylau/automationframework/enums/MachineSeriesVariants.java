package com.epam.training.artsiom_shylau.automationframework.enums;

public enum MachineSeriesVariants implements VariantForSelection {

    N1("N1", "n1"),
    N2("N2", "n2");

    private String variantText;
    private String variantValue;

    MachineSeriesVariants(String variantText, String variantValue) {
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
