package com.healthmate.service.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthmate.service.dynamodb.models.Day;
import com.healthmate.service.dynamodb.models.TimeRange;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ScheduleConverter implements DynamoDBTypeConverter<String, Map<Day, List<TimeRange>>> {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<Map<Day, List<TimeRange>>> TYPE_REFERENCE = new TypeReference<Map<Day, List<TimeRange>>>() {};

    @Override
    public String convert(Map<Day, List<TimeRange>> dayListMap) {
        try {
            return objectMapper.writeValueAsString(dayListMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Day, List<TimeRange>> unconvert(String s) {
        try {
            return objectMapper.readValue(s, TYPE_REFERENCE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
