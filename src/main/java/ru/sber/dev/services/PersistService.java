package ru.sber.dev.services;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.sber.dev.models.db.PersistedMessage;
import ru.sber.dev.models.db.repositories.PersistedMessageRepository;
import ru.sber.dev.models.kafka.MessageWrapper;
import ru.sber.dev.models.kafka.SimpleMessage;

@Service
@Slf4j
public class PersistService implements IPersistService {

    private final PersistedMessageRepository repository;

    public PersistService(@Autowired PersistedMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveBatch(final List<MessageWrapper> batch, final Date date) {
        final Iterable<PersistedMessage> persistedMessages = repository.saveAll(batch.stream()
                .flatMap(messageWrapper -> messageWrapper.getMessages().stream()
                        .map(simpleMessage -> convertMessage(simpleMessage, date)))
                .collect(Collectors.toSet()));
        log.info("Saved entities: {}", persistedMessages);
    }

    private PersistedMessage convertMessage(final SimpleMessage src, final Date date) {
        return PersistedMessage.builder()
                .id(src.getMessageId())
                .payload(src.getPayload())
                .createDate(date)
                .build();
    }
}

