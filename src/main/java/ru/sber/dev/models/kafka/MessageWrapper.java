package ru.sber.dev.models.kafka;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MessageWrapper {

    @Valid
    @NotEmpty
    private List<SimpleMessage> messages;
}
