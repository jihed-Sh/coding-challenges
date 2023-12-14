package com.example.kafkademo.consumer;

import com.example.kafkademo.payload.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = "MyTopic", groupId = "myGroup")
    public void consumeMessage(Student student) {
        log.info(format("Reading message from my topic: %s",student.toString()));


    }
}
