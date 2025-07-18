package net.todream.uni_translate.uni_translate.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.todream.uni_translate.uni_translate.dto.TranslateWithReturnInDto;

@Component
@Slf4j
public class TranslateCacheConsumer {

    @KafkaListener(topics = "translate_return", groupId = "translate_return_group")
    public void translateWithReturn(TranslateWithReturnInDto in, Acknowledgment ack) {
        log.info("收到翻译返回对象: {}", in);
        ack.acknowledge(); // 手动提交 offset
    }

}
