package net.todream.uni_translate.uni_translate.service;

public interface KafkaProducerService {

    public void send(String topic, Object obj);

    public void send(String topic, String key, Object obj);

}
