package com.epam.training.artsiom_shylau.automationframework.enums.cloudgoogle;

public enum MachineClassVariants {

    REGULAR("select_option_96"),
    PREEMPTIBLE("select_option_97");

    private String machineClassOptionId;

    MachineClassVariants(String id) {
        this.machineClassOptionId = id;
    }

    public String getMachineClassOptionId() {
        return machineClassOptionId;
    }
}
