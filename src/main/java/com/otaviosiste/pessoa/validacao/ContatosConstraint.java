package com.otaviosiste.pessoa.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Constraint(validatedBy = ContatosConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContatosConstraint {

	String message() default "Pessoa deve ter ao menos um contato.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
