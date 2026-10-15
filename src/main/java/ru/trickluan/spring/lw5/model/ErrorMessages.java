package ru.trickluan.spring.lw5.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {
    EMPTY(""),
    VALIDATION("Ошибка валидации"),
    UNSUPPORTED("Произошла непредвиденная ошибка"),
    UNKNOWN("Неподдерживаемая ошибка");

    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
