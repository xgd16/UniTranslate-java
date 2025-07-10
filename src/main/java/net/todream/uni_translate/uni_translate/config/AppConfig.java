package net.todream.uni_translate.uni_translate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public LanguageConfig languageConfig() {
        return new LanguageConfig();
    }

}
