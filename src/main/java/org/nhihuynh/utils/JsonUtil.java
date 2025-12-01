package org.nhihuynh.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtil {
    /**
     * Generic method to read JSON and convert to List of Objects.
     * @param jsonFilePath Path to the JSON file
     * @param typeReference Jackson type reference (to handle List<T>)
     * @return List of mapped objects
     * T is a generic placeholder
     */
    public static <T> List<T> getJsonData(String jsonFilePath, TypeReference<List<T>> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(jsonFilePath), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read test data from: " + jsonFilePath);
        }
    }
}
