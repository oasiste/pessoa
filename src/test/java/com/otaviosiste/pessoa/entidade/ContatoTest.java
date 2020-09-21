package com.otaviosiste.pessoa.entidade;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class ContatoTest {

	private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void testContatoSuccess() {
        Contato contact = new Contato("test","4433334444","teste@teste.com");
        Set<ConstraintViolation<Contato>> violations = validator.validate(contact);
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void testContatoEmailInvalido() {
        Contato contact = new Contato("test","4433334444","teste/teste.com");
        Set<ConstraintViolation<Contato>> violations = validator.validate(contact);
        assertFalse(violations.isEmpty());
        assertEquals("Email deve ser valido", violations.stream().map(v->v.getMessage()).findFirst().get());
    }

}
