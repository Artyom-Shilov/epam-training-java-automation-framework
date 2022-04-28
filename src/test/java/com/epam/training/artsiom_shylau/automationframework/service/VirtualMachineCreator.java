package com.epam.training.artsiom_shylau.automationframework.service;

import com.epam.training.artsiom_shylau.automationframework.model.GPU;
import com.epam.training.artsiom_shylau.automationframework.model.VirtualMachine;

public class VirtualMachineCreator {

    private static final String MACHINE_TYPE_KEY = "testdata.machine.type";
    private static final String MACHINE_CLASS_KEY = "testdata.machine.class";
    private static final String MACHINE_NUMBER_KEY  = "testdata.machine.number";
    private static final String MACHINE_OS_KEY  = "testdata.machine.OS";


    private VirtualMachineCreator() {}

    public static VirtualMachine createVirtualMachineWithDataFromProperty() {
        return new VirtualMachine(
                Integer.parseInt(MACHINE_NUMBER_KEY),
                TestDataReader.getTestData(MACHINE_TYPE_KEY),
                TestDataReader.getTestData(MACHINE_CLASS_KEY),
                TestDataReader.getTestData(MACHINE_OS_KEY)
        );
    }
}
