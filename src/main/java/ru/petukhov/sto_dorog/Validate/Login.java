package ru.petukhov.sto_dorog.Validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonLoginValidator.class)
public @interface Login {
    String message() default "логин уже занят";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
