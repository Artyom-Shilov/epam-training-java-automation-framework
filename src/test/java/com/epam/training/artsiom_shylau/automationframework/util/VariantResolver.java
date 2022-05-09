package com.epam.training.artsiom_shylau.automationframework.util;

import com.epam.training.artsiom_shylau.automationframework.enums.VariantForSelection;

import java.util.Arrays;

public class VariantResolver {

    private VariantResolver(){}

    public static String getVariantValueByVariantText(VariantForSelection[] variants, String variantText) {
        return Arrays.stream(variants)
                .filter(v -> v.getVariantText().equalsIgnoreCase(variantText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException())
                .getVariantValue();
    }
}
