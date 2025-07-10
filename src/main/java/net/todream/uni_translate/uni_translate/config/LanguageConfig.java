package net.todream.uni_translate.uni_translate.config;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

public class LanguageConfig {

    @Resource
    private ObjectMapper objectMapper;
    
    @PostConstruct
    public void init() {

        try {
            Map<String, Map<String, String>> map = objectMapper.readValue(new File("./language.json"), new TypeReference<Map<String, Map<String, String>>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load language configuration", e);
        }
    }


}
