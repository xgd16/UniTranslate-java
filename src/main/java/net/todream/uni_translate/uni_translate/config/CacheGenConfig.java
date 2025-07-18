package net.todream.uni_translate.uni_translate.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.utils.Common;

@Configuration
public class CacheGenConfig {
    
    @Bean(name = "translateMd5PlatformCacheGen")
    public KeyGenerator translateMd5PlatformCacheGen() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getName()).append(":");
            for (Object param : params) {
                if (param != null && param instanceof TranslateClientInDto) {
                    StringBuilder keyBody = new StringBuilder();
                    TranslateClientInDto in = (TranslateClientInDto) param;
                    String from = in.getFrom();
                    if (from.isEmpty()) {
                        from = "auto";
                    }
                    keyBody.append(in.getPlatform()).append("_")
                          .append(from).append("_")
                          .append(in.getTo()).append("_")
                          .append(in.getText());
                    sb.append(keyBody.toString());
                }
            }
            return Common.md5(sb.toString());
        };
    }
    

}
