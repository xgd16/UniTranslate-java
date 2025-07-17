package net.todream.uni_translate.uni_translate.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.todream.uni_translate.uni_translate.service.KafkaProducerService;

@Slf4j
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void send(String topic, Object obj) {
        //发送消息
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, obj);
        // 处理成功和失败
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("发送消息成功：" + result.getRecordMetadata());
            } else {
                log.error("发送消息失败：" + ex.getMessage());
            }
        });
    }

    @Override
    public void send(String topic, String key, Object obj) {
             //发送消息
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, obj);
        // 处理成功和失败
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("发送消息成功：" + result.getRecordMetadata());
            } else {
                log.error("发送消息失败：" + ex.getMessage());
            }
        });
    }

}
