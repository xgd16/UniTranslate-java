package net.todream.uni_translate.uni_translate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaListenerConfig {

    /**
     * 处理kafka出错无限重试
     * @return
     */
    @Bean
    public DefaultErrorHandler kafkaErrorHandler() {
        // 3次重试，每次间隔1秒（1000毫秒）
        return new DefaultErrorHandler(new FixedBackOff(1000L, 3));
    }

}
