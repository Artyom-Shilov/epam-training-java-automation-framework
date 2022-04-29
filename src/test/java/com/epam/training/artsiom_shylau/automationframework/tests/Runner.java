package com.epam.training.artsiom_shylau.automationframework.tests;

import com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.DatacenterLocationVariants;
import com.epam.training.artsiom_shylau.automationframework.util.TestListener;

import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
      //  System.out.println(DatacenterLocationVariants.valueOf("frankfut".toUpperCase()).getDatacenterLocationOptionId());
        String s = "1234{%s}";
        s = String.format(s, "tt");

        ResourceBundle bundle = ResourceBundle.getBundle("variantsid");
        System.out.println(bundle.getString("usage.duration.1"));

    }
}
