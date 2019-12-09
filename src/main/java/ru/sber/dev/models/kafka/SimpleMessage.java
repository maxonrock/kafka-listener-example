package ru.sber.dev.models.kafka;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import ru.sber.dev.validators.MessageNotExists;

@Data
@MessageNotExists
public class SimpleMessage {
    @NotNull
    @PositiveOrZero
    private Long messageId;
    private String payload;
}
