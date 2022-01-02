package com.example.simple.library.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.example.simple.library.utils.Constants.KAFKA_EMAIL_SEND_TOPIC;

@Service
public class MailEventConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(MailEventConsumerService.class);

    @KafkaListener(topics = KAFKA_EMAIL_SEND_TOPIC)
    public void processEmailSend(String email) {

        LocalDateTime startTime = LocalDateTime.now();
        logger.info("processEmailSend: consumed process for: " + email);

        logger.info("SENT EMAIL");

        logger.info("processEmailSend: finished processing " +
                ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()) + " ms");
    }
}
