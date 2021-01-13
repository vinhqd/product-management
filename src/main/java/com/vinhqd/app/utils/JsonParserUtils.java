package com.vinhqd.app.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class JsonParserUtils {

    public Map<Integer, String> getProvinces(String fileName) {
        ClassPathResource classPathResource = new ClassPathResource("static/json/"+ fileName);
        Map<Integer, String> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileReader reader = new FileReader(classPathResource.getFile());
            BufferedReader bufferedReader = new BufferedReader(reader);
            JsonNode jsonNode = mapper.readTree(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
