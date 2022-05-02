package com.epam.training.artsiom_shylau.automationframework.util;

public class StringOperations {

    private StringOperations(){}

    public static String formMessageForVariantSelectionException(String option) {
        return option + ": variant is unknown";
    }

    public static String getSubstring(String text, String from, String to) {
        return text.substring(text.indexOf(from) + from.length(), text.indexOf(to));
    }

    public static String getSubstring(String text, String from) {
        return text.substring(text.indexOf(from) + from.length());
    }

}
