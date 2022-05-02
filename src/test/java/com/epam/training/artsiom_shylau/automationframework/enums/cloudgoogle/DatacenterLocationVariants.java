package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

import com.epam.training.artsiom_shylau.automationframework.exceptions.VariantSelectionException;
import com.epam.training.artsiom_shylau.automationframework.util.StringOperations;

import java.util.Arrays;

public enum DatacenterLocationVariants {

    FRANKFURT("Frankfurt", "select_option_242"),
    LOS_ANGELES("Los Angeles", "select_option_237");

    private String locationTextValue;
    private String datacenterLocationOptionId;

    DatacenterLocationVariants(String locationTextValue, String datacenterLocationOptionId) {
        this.locationTextValue = locationTextValue;
        this.datacenterLocationOptionId = datacenterLocationOptionId;
    }

    public String getLocationTextValue() {
        return locationTextValue;
    }


    public String getDatacenterLocationOptionId() {
        return datacenterLocationOptionId;
    }

    public static String getDatacenterLocationOptionIdByTextValue(String textValue) throws VariantSelectionException {
        return Arrays.stream(DatacenterLocationVariants.values())
                .filter(v -> v.getLocationTextValue().equalsIgnoreCase(textValue))
                .findFirst()
                .orElseThrow(() -> new VariantSelectionException(StringOperations.formMessageForVariantSelectionException(textValue)))
                .getDatacenterLocationOptionId();
    }

}
