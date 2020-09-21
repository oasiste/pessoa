package com.otaviosiste.pessoa.entidade;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.otaviosiste.pessoa.validacao.ContatosConstraint;
import com.otaviosiste.pessoa.validacao.CpfValido;

@Entity
public class Pessoa {

	private @Id @GeneratedValue Long id;

	@Column(nullable = false)
	public String nome;

	@CpfValido
	@Column(nullable = false)
	public String cpf;

	@JsonFormat(pattern="dd/MM/yyyy")
	@PastOrPresent
	@Column(nullable = false)
	public Date dataNascimento;

	@Valid
	@ContatosConstraint
	@ElementCollection
	@CollectionTable(
			name = "CONTATO",
			joinColumns = @JoinColumn(name = "ID")
			)
	public List<Contato> contatos;

	Pessoa() {};

	public void atualiza(Pessoa novaPessoa) {
		this.nome = novaPessoa.getNome();
		this.cpf = novaPessoa.getCpf();
		this.dataNascimento = novaPessoa.getDataNascimento();
		this.contatos = novaPessoa.getContatos();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pessoa) {
			Pessoa other = (Pessoa) obj;
			return Objects.equals(this.nome, other.nome) &&
					Objects.equals(this.cpf, other.cpf) &&
					Objects.equals(this.dataNascimento, other.dataNascimento);
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome,cpf,dataNascimento);
	}

	public Pessoa(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

}
