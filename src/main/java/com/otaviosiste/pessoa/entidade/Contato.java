package com.otaviosiste.pessoa.entidade;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

@Embeddable
public class Contato {
		
	@Column(nullable = false)
	public String nome;
	
	@Column(nullable = false)
	public String telefone;
	
	@Email(message = "Email deve ser valido")
	@Column(nullable = false)
	public String email;
	
	Contato(){};
	
	public Contato(String nome, String telefone, String email) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Contato) {
			Contato other = (Contato) obj;
			return Objects.equals(this.nome, other.nome) &&
					Objects.equals(this.telefone, other.telefone) &&
					Objects.equals(this.email, other.email);
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome,telefone,email);
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}
}
