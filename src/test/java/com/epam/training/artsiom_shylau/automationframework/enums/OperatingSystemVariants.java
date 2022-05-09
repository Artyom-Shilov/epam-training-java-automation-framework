package com.epam.training.artsiom_shylau.automationframework.enums;

public enum OperatingSystemVariants implements VariantForSelection {

    FREE("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)","free"),
    UBUNTU_PRO("Paid: Ubuntu Pro", "ubuntu-pro");

    private String variantText;
    private String variantValue;

    OperatingSystemVariants(String variantText, String variantValue) {
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
