package com.epam.training.artsiom_shylau.automationframework.model;

import java.util.Objects;

public class Datacenter {

    private String location;

    public Datacenter(String cityName) {
        this.location = cityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datacenter that = (Datacenter) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "Datacenter{" +
                "location='" + location + '\'' +
                '}';
    }
}
