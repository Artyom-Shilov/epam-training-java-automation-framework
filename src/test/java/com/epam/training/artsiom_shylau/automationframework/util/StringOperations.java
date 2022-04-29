package com.epam.training.artsiom_shylau.automationframework.util;

public class StringOperations {

    private StringOperations(){}

    public static String formMessageForVariantSelectionException(String option) {
        return option + ": variant is unknown";
    }

}
