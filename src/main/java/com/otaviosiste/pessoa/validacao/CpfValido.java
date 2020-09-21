package com.otaviosiste.pessoa.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfValidoValidator.class)
@Documented
public @interface CpfValido {
	
	String message() default "CPF invalido";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
