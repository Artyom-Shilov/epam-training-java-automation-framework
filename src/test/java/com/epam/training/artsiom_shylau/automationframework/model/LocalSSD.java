package com.epam.training.artsiom_shylau.automationframework.model;

import java.util.Objects;

public class LocalSSD {

    String capacity;

    public LocalSSD(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalSSD localSSD = (LocalSSD) o;
        return Objects.equals(capacity, localSSD.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }

    @Override
    public String toString() {
        return "LocalSSD{" +
                "capacity='" + capacity + '\'' +
                '}';
    }
}
