package com.example.notificationservice.controllers;

import com.example.notificationservice.Consumers.MessageConsumer;
import com.example.notificationservice.Producers.MessageProducer;
import com.example.notificationservice.dtos.MessageRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {
        private final MessageProducer messageProducer;
        private final KafkaTemplate<String, MessageRequestDto> kafkaTemplate;
        @Autowired
        KafkaController (MessageProducer messageProducer, MessageProducer messageProducer1, KafkaTemplate<String,MessageRequestDto> kafkaTemplate){
            this.messageProducer = messageProducer1;
            this.kafkaTemplate=kafkaTemplate;
        }

        @PostMapping("/send")
        public String sendMessage(@RequestBody MessageRequestDto messageRequest){
            messageProducer.sendMessage("my-topic-2",messageRequest);
            return "Message has been send";
        }
}
