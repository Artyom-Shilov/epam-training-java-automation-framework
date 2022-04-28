package com.epam.training.artsiom_shylau.automationframework.tests;

import com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle.DatacenterLocationVariants;

public class Runner {
    public static void main(String[] args) {
        System.out.println(DatacenterLocationVariants.valueOf("frankfut".toUpperCase()).getDatacenterLocationOptionId());
        String s = "1234{%s}";
        s = String.format(s, "tt");

        System.out.println(s);

    }
}
