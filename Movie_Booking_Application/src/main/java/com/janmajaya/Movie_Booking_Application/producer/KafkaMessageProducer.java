package com.janmajaya.Movie_Booking_Application.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducer {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public void pushMessage(String message){
        kafkaTemplate.send("topic1",message);
    }
}
