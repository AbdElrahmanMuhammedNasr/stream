package com.project.stream.service.impl;

import com.project.stream.config.kafka.MessageUploadVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "upload";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(MessageUploadVideo message) {
        kafkaTemplate.send(TOPIC, message);
    }
}

