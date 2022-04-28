package com.epam.training.artsiom_shylau.automationframework.model;

public class GPU {

    private String typeOfGPU;
    private int numberOfGPU;

    public GPU(String typeOfGPU, int numberOfGPU) {
        this.typeOfGPU = typeOfGPU;
        this.numberOfGPU = numberOfGPU;
    }

    public String getTypeOfGPU() {
        return typeOfGPU;
    }

    public void setTypeOfGPU(String typeOfGPU) {
        this.typeOfGPU = typeOfGPU;
    }

    public int getNumberOfGPU() {
        return numberOfGPU;
    }

    public void setNumberOfGPU(int numberOfGPU) {
        this.numberOfGPU = numberOfGPU;
    }
}
