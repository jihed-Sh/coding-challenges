package com.example.producer.rest;

import com.example.producer.payload.Student;
import com.example.producer.producer.KafkaJsonProducer;
import com.example.producer.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private  final KafkaProducer producer;
    private  final KafkaJsonProducer jsonProducer;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody String message){
        producer.publishMessage(message);
        return ResponseEntity.ok("Message queued successfully");
    }
    @PostMapping("/json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Student student){
        jsonProducer.sendStudent(student);
        return ResponseEntity.ok("Json Message queued successfully");
    }
}
