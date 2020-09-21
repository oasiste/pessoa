package com.otaviosiste.pessoa.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import com.otaviosiste.pessoa.entidade.Contato;
import com.otaviosiste.pessoa.entidade.Pessoa;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void testNovaPessoa() {
		ResponseEntity<Pessoa> responseEntity =
				restTemplate.postForEntity("/pessoas", PessoaProvider.otavio(), Pessoa.class);
		Pessoa pessoa = responseEntity.getBody();
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(Long.valueOf(1), pessoa.getId());
	}
	
	@Test
	@Order(2)
	void testGetPessoaExistente() {
		ResponseEntity<Pessoa> responseEntity = restTemplate.getForEntity("/pessoas/1", Pessoa.class);
		Pessoa pessoa = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(Long.valueOf(1), pessoa.getId());
	}
	
	
	@Test
	@Order(3)
	void testAtualizaPessoa() {
		Pessoa pessoa = PessoaProvider.otavio();
		pessoa.setNome("Otavio Augusto");
		HttpEntity<Pessoa> requestUpdate = new HttpEntity<>(pessoa, new HttpHeaders());

		ResponseEntity<Pessoa> responseEntity = restTemplate.exchange("/pessoas/1",HttpMethod.PUT,requestUpdate,Pessoa.class);
		pessoa = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(Long.valueOf(1), pessoa.getId());
		assertEquals("Otavio Augusto", pessoa.getNome());
	}
	
	
	@Test
	@Order(4)
	void deletaPessoa() {
		restTemplate.delete("/pessoas/1");
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/pessoas/1", String.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals(responseEntity.getBody(),"Pessoa nao encontrada 1");
	}
	
	
	@Test
	@Order(5)
	void testGetPessoaInexistente() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/pessoas/1", String.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals(responseEntity.getBody(),"Pessoa nao encontrada 1");	
	}
	
	@Test
	@Order(6)
	void testNovaPessoaCpfInvalido() {
		Pessoa pessoa = PessoaProvider.otavio();
		pessoa.setCpf("00000000000");
		ResponseEntity<String> responseEntity =
				restTemplate.postForEntity("/pessoas", pessoa, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(7)
	void testNovaPessoaSemContato() {
		Pessoa pessoa = PessoaProvider.otavio();
		pessoa.setContatos(new ArrayList<>());
		ResponseEntity<String> responseEntity =
				restTemplate.postForEntity("/pessoas", pessoa, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(8)
	void testNovaPessoaDataNascimentoInvalido() {
		Pessoa pessoa = PessoaProvider.otavio();
		pessoa.setDataNascimento(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
		ResponseEntity<String> responseEntity =
				restTemplate.postForEntity("/pessoas", pessoa, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(9)
	void testNovaPessoaEmailInvalido() {
		Pessoa pessoa = PessoaProvider.otavio();
		pessoa.getContatos().add(new Contato("teste", "0011112222", "emailinvalido"));
		ResponseEntity<String> responseEntity =
				restTemplate.postForEntity("/pessoas", pessoa, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	

}
