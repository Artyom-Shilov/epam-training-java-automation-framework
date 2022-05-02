package com.epam.training.artsiom_shylau.automationframework.model;

import java.util.Objects;

public class UsageTerm {

    private String duration;
    private String price;

    public UsageTerm(String duration, String price) {
        this.duration = duration;
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsageTerm usageTerm = (UsageTerm) o;
        return Objects.equals(duration, usageTerm.duration) && Objects.equals(price, usageTerm.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, price);
    }

    @Override
    public String toString() {
        return "UsageTerm{" +
                "duration='" + duration + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
