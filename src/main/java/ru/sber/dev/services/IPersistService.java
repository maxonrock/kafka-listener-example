package ru.sber.dev.services;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import ru.sber.dev.models.kafka.MessageWrapper;

@Validated
public interface IPersistService {
    void saveBatch(@Valid List<MessageWrapper> batch, @Valid @NotNull Date date);
}
