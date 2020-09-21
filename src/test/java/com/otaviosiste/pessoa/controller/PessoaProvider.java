package com.otaviosiste.pessoa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.otaviosiste.pessoa.entidade.Contato;
import com.otaviosiste.pessoa.entidade.Pessoa;

public class PessoaProvider {
	
	public static Pessoa otavio() {
		
		Pessoa pessoa = new Pessoa(1L);
		pessoa.setNome("OTAVIO");
		pessoa.setCpf("95916349009");
		pessoa.setDataNascimento(new Date());
		List<Contato> contatos = new ArrayList<Contato>();
		contatos.add(new Contato("teste", "44222233333", "teste@teste.com"));
		pessoa.setContatos(contatos);
		
		return pessoa;
	} 

}
