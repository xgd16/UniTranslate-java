package net.todream.uni_translate.uni_translate.config;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Data;
import net.todream.uni_translate.uni_translate.exception.LanguageException;

@Data
public class LanguageConfig {

    @Resource
    private ObjectMapper objectMapper;
    
    private ConcurrentHashMap<String, ConcurrentHashMap<String, String>> languageMap;
    
    @PostConstruct
    public void init() {
        try {
            Map<String, Map<String, String>> map = objectMapper.readValue(
                new File("./language.json"), 
                new TypeReference<Map<String, Map<String, String>>>() {}
            );
            ConcurrentHashMap<String, ConcurrentHashMap<String, String>> concurrentMap = new ConcurrentHashMap<>();
            for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
                concurrentMap.put(entry.getKey(), new ConcurrentHashMap<>(entry.getValue()));
            }
            this.languageMap = concurrentMap;
        } catch (Exception e) {
            throw new LanguageException("Failed to load language configuration", e);
        }
    }
    /**
     * 获取指定平台和语言代码的语言名称
     * @param platform 平台
     * @param langCode 语言代码
     * @throws LanguageException 如果平台或语言代码不支持
     * @return
     */
    public String getLang(String platform, String langCode) {
        if (!languageMap.containsKey(platform)) {
            throw new LanguageException(platform + " is not supported");
        }
        ConcurrentHashMap<String, String> langCodeMap = languageMap.get(platform);
        if (!langCodeMap.containsKey(langCode)) {
            throw new LanguageException("Language code " + langCode + " is not supported for platform " + platform);
        }
        return langCodeMap.get(langCode);
    }

}
