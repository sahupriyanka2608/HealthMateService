package com.healthmate.service.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DoctorsInDeptConverter implements DynamoDBTypeConverter<String, Map<String, List<String>>> {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<Map<String, List<String>>> TYPE_REFERENCE = new TypeReference<Map<String, List<String>>>() {};

    @Override
    public String convert(Map<String, List<String>> stringListMap) {
        try {
            return objectMapper.writeValueAsString(stringListMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @DynamoDBIgnore
    public Map<String, List<String>> unconvert(String s) {
        try {
            return objectMapper.readValue(s, TYPE_REFERENCE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
