package com.healthmate.service.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthmate.service.dynamodb.models.Availability;
import com.healthmate.service.dynamodb.models.LocalDateMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AvailableSlotConverter implements DynamoDBTypeConverter<String, Map<LocalDateMapper, List<Availability>>> {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<Map<LocalDateMapper, List<Availability>>> TYPE_REFERENCE = new TypeReference<Map<LocalDateMapper, List<Availability>>>() {};
    @Override
    public String convert(Map<LocalDateMapper, List<Availability>> localDateMapperListMap) {
        try {
            return objectMapper.writeValueAsString(localDateMapperListMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<LocalDateMapper, List<Availability>> unconvert(String s) {
        try {
            return objectMapper.readValue(s, TYPE_REFERENCE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
