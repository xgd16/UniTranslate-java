package net.todream.uni_translate.uni_translate.config;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.todream.uni_translate.uni_translate.entity.TimestampInterceptor;

@Configuration
public class MyBatisConfig {
    @Bean
    public Interceptor timestampInterceptor() {
        return new TimestampInterceptor();
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer(Interceptor timestampInterceptor) {
        return configuration -> {
            configuration.addInterceptor(timestampInterceptor);
            
            // 注册 ObjectTypeHandler
            // TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            // typeHandlerRegistry.register(Object.class, ObjectTypeHandler.class);
        };
    }
} 