package com.epam.training.artsiom_shylau.automationframework.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPU gpu = (GPU) o;
        return numberOfGPU == gpu.numberOfGPU && Objects.equals(typeOfGPU, gpu.typeOfGPU);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfGPU, numberOfGPU);
    }

    @Override
    public String toString() {
        return "GPU{" +
                "typeOfGPU='" + typeOfGPU + '\'' +
                ", numberOfGPU=" + numberOfGPU +
                '}';
    }
}
