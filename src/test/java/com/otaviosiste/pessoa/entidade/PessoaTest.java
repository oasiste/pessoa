package com.otaviosiste.pessoa.entidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.otaviosiste.pessoa.controller.PessoaProvider;

public class PessoaTest {

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	@Test
	public void testPessoaSucesso() {
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(PessoaProvider.otavio());
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testPessoaCpfInvalido() {
		Pessoa pessoa = PessoaProvider.otavio();
		pessoa.setCpf("00000000000");
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);
		assertFalse(violations.isEmpty());
		assertEquals("CPF invalido", violations.stream().map(v->v.getMessage()).findFirst().get());
	}
	
	@Test
	public void testPessoaSemContatos() {
		Pessoa pessoa = PessoaProvider.otavio();
		pessoa.setContatos(Collections.emptyList());
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);
		assertFalse(violations.isEmpty());
		assertEquals("Pessoa deve ter ao menos um contato.", violations.stream().map(v->v.getMessage()).findFirst().get());
	}

}
