package com.lucas.curso.springboot.app.springboot_crud.validation;

import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;

@Constraint(validatedBy = existsByUsernameValidation.class )
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface existsByUsername {

    String message() default "ya se encuentra ocupado";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
