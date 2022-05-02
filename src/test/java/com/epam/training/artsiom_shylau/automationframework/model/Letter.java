package com.epam.training.artsiom_shylau.automationframework.model;

import java.util.Objects;

public class Letter {

    private String text;
    private String theme;
    private String sender;

    public Letter(String text, String theme, String sender) {
        this.text = text;
        this.theme = theme;
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return Objects.equals(text, letter.text) && Objects.equals(theme, letter.theme) && Objects.equals(sender, letter.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, theme, sender);
    }

    @Override
    public String toString() {
        return "Letter{" +
                "text='" + text + '\'' +
                ", theme='" + theme + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
