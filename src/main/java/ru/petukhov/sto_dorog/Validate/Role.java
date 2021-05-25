package ru.petukhov.sto_dorog.Validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleValidator.class)
public @interface Role {
    String message() default "Укажите роль: 'ROLE_SUPER-ADMIN', 'ROLE_ADMIN', 'ROLE_USER'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
