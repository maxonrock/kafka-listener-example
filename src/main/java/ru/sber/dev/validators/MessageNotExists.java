package ru.sber.dev.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MessageNotExistsValidator.class)
public @interface MessageNotExists {
    String message() default "Message with id=${validatedValue.getMessageId()} already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
