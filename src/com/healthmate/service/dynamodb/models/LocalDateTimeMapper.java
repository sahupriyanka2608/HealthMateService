package com.healthmate.service.dynamodb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalDateTimeMapper {
    private String date;
    private String time;
    public LocalDate getLocalDate() {
        return LocalDate.parse(date);
    }
}
