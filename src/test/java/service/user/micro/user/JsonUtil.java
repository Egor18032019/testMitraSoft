package service.user.micro.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JsonUtil {


    final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> readValues(String json, Class<T> clazz) throws IOException {
        // добавили модуль что бы читал время
        objectMapper.registerModule(new JavaTimeModule());
        ObjectReader reader = objectMapper.readerFor(clazz);
        return reader.<T>readValues(json).readAll();
    }

    public static String convertJson(HashMap<String, HashMap<String, String>> map) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(map);
        return json;
    }

    public static HashMap convertHashMap(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, HashMap.class);
    }

    public static <T> String writeValue(T obj) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        String json =objectMapper.writeValueAsString(obj);
        System.out.println("json");
        System.out.println(json);
        return json;
    }

    public static <T> T readValue(String json, Class<T> clazz) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.readValue(json, clazz);
    }
}