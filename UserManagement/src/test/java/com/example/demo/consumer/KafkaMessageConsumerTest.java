package com.example.demo.consumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KafkaMessageConsumer.class})
@ExtendWith(SpringExtension.class)
class KafkaMessageConsumerTest {
    @Autowired
    private KafkaMessageConsumer kafkaMessageConsumer;


    @Test
    void testListen() {

        kafkaMessageConsumer.listen(new ConsumerRecord<>("Topic", 1, 1L, "Key", "42"));
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testListen2() {

        kafkaMessageConsumer.listen(null);
    }

    @Test
    void testListen3() {
        ConsumerRecord<String, String> event = mock(ConsumerRecord.class);
        when(event.value()).thenReturn("42");
        kafkaMessageConsumer.listen(event);
        verify(event).value();
    }
}

