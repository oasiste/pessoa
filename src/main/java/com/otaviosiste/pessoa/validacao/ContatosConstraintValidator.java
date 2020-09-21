package com.otaviosiste.pessoa.validacao;

import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.otaviosiste.pessoa.entidade.Contato;

public class ContatosConstraintValidator implements ConstraintValidator<ContatosConstraint, List<Contato>> {

	@Override
	public boolean isValid(List<Contato> contatos, ConstraintValidatorContext context) {		
		return Objects.nonNull(contatos) && contatos.size() > 0 ;
	}

}