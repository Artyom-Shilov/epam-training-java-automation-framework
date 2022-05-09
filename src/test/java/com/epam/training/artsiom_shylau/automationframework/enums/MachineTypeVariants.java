package com.epam.training.artsiom_shylau.automationframework.enums;

public enum MachineTypeVariants implements VariantForSelection {

    N1_STANDART_8("n1-standard-8", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8"),
    N1_HIGHMEM_4("n1-highmem-4", "CP-COMPUTEENGINE-VMIMAGE-N1-HIGHMEM-4"),
    N2_STANDART_16("n2-standard-16", "CP-COMPUTEENGINE-VMIMAGE-N2-STANDARD-16");

    private String variantText;
    private String variantValue;

    MachineTypeVariants (String variantText, String variantValue) {
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
