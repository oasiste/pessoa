package com.otaviosiste.pessoa.controller;

public class PessoaNotFoundException extends RuntimeException {

	PessoaNotFoundException(Long id) {
	    super("Pessoa nao encontrada " + id);
	  }

}
