package com.otaviosiste.pessoa.validacao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.otaviosiste.pessoa.entidade.Contato;

public class ContatosConstraintValidatorTest {
	
	static ContatosConstraintValidator contatosConstraintValidator;
	
	@BeforeClass
	public static void before() {
		contatosConstraintValidator = new ContatosConstraintValidator();
	}

	@Test
	public void testIsValid() {
		
		List<Contato> contatos = new ArrayList<Contato>();
		contatos.add(new Contato("test","4411112222","test@test.com"));
		assertTrue(contatosConstraintValidator.isValid(contatos, null));
	}
	
	@Test
	public void testIsInvalid() {
		assertFalse(contatosConstraintValidator.isValid(new ArrayList<Contato>(), null));
	}

}
