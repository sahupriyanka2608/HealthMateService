package com.healthmate.service.dynamodb.models;

import java.time.LocalDate;
import java.util.Objects;

public class LocalDateMapper {
    private String date;
    private LocalDateMapper() {}
    public LocalDateMapper(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public LocalDate convertToLocalDate() {
        return LocalDate.parse(date);
    }

    public Day getDayOfWeek() {
        return Day.getDay(convertToLocalDate().getDayOfWeek());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalDateMapper)) {
            return false;
        }
        return Objects.equals(getDate(), ((LocalDateMapper) o).getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate());
    }

    @Override
    public String toString() {
        return date;
    }
}
