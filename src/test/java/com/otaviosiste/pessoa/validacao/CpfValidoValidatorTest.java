package com.otaviosiste.pessoa.validacao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class CpfValidoValidatorTest {
	
	
	static CpfValidoValidator cpfValidator;
	
	@BeforeClass
	public static void before() {
		cpfValidator = new CpfValidoValidator();
	} 

	@Test
	public void testIsValido() {
		assertTrue(cpfValidator.isValid("22363623070", null));		
	}
	
	@Test
	public void testIsInvalido() {
		assertFalse(cpfValidator.isValid("11111111111", null));		
		assertFalse(cpfValidator.isValid("22363623080", null));
	}

}
