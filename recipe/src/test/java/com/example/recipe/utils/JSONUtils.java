package com.example.recipe.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;

public class JSONUtils {

    public Object getFakeResponseFromLocalJson(String filePath) {
        try {
            File file = ResourceUtils.getFile(getClass().getResource(filePath));
            ObjectMapper mapper = new ObjectMapper();
            Object response = mapper.readValue(file, Object.class);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
