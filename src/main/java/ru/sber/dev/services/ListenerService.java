package ru.sber.dev.services;


import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.sber.dev.config.KafkaConfig;
import ru.sber.dev.models.kafka.MessageWrapper;

@Service
@Slf4j
public class ListenerService {
    private final IPersistService persistService;

    public ListenerService(@Autowired IPersistService persistService) {
        this.persistService = persistService;
    }

    @KafkaListener(topics = { "${kafka.topics.inbound}" }, containerFactory = KafkaConfig.BATCH_LISTENER_CONTAINER_FACTORY)
    public void consumeResponse(List<MessageWrapper> batch,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long receivedTimestamp,
            Acknowledgment ack) {
        log.info("Received message: " + batch);
        try {
            final Date date = new Date(receivedTimestamp);
            persistService.saveBatch(batch, date);
        } catch (ConstraintViolationException ex) {
            log.warn("{} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
        ack.acknowledge();
    }
}
