package ru.sber.dev.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.sber.dev.models.db.repositories.PersistedMessageRepository;
import ru.sber.dev.models.kafka.SimpleMessage;

public class MessageNotExistsValidator implements ConstraintValidator<MessageNotExists, SimpleMessage> {
    @Autowired
    private PersistedMessageRepository repository;

    @Override
    public boolean isValid(final SimpleMessage message, final ConstraintValidatorContext context) {
        return !repository.existsById(message.getMessageId());
    }
}
