package com.epam.training.artsiom_shylau.automationframework.enums;

public enum MachineClassVariants implements VariantForSelection {

    REGULAR("Regular","regular"),
    PREEMPTIBLE("Preemptible", "preemptible");

    private String variantText;
    private String variantValue;

    MachineClassVariants(String variantText, String variantValue) {
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
