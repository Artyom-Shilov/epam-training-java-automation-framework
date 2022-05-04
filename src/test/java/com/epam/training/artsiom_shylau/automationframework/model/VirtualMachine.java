package com.epam.training.artsiom_shylau.automationframework.model;

import java.util.Objects;

public class VirtualMachine {

    private int numberOfInstances;
    private String machineClass;
    private String machineType;
    private String operatingSystem;

    public VirtualMachine(int numberOfInstances, String machineClass, String machineType, String operationSystem) {
        this.numberOfInstances = numberOfInstances;
        this.machineClass = machineClass;
        this.machineType = machineType;
        this.operatingSystem = operationSystem;
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

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirtualMachine that = (VirtualMachine) o;
        return numberOfInstances == that.numberOfInstances && Objects.equals(machineClass, that.machineClass) && Objects.equals(machineType, that.machineType) && Objects.equals(operatingSystem, that.operatingSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, machineClass, machineType, operatingSystem);
    }

    @Override
    public String toString() {
        return "VirtualMachine{" +
                "numberOfInstances=" + numberOfInstances +
                ", machineClass='" + machineClass + '\'' +
                ", machineType='" + machineType + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }


}
