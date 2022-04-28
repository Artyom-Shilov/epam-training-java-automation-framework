package com.epam.training.artsiom_shylau.automationframework.model;

public class VirtualMachine {

    private int numberOfInstances;
    private String machineClass;
    private String machineType;
    private String operationSystem;

    public VirtualMachine(int numberOfInstances, String machineClass, String machineType, String operationSystem) {
        this.numberOfInstances = numberOfInstances;
        this.machineClass = machineClass;
        this.machineType = machineType;
        this.operationSystem = operationSystem;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getMachineClass() {
        return machineClass;
    }

    public void setMachineClass(String machineClass) {
        this.machineClass = machineClass;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }
}
