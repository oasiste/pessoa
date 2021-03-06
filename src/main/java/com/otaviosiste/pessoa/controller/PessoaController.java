package com.otaviosiste.pessoa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.otaviosiste.pessoa.entidade.Pessoa;
import com.otaviosiste.pessoa.repository.PessoaRepository;

@RestController
public class PessoaController {

	private final PessoaRepository repository;

	PessoaController(PessoaRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/pessoas")
    public List<Pessoa> search(
            @RequestParam("nome") String nome,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    required = false,
                    defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy) {
        return repository.findAllByNome(nome, PageRequest.of(page, size));
    }

	@PostMapping("/pessoas")
	@ResponseStatus(value = HttpStatus.CREATED)
	Pessoa novaPessoa(@RequestBody Pessoa novaPessoa) {
		return repository.save(novaPessoa);
	}

	@GetMapping("/pessoas/{id}")
	Pessoa one(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new PessoaNotFoundException(id));
	}

	@PutMapping("/pessoas/{id}")
	Pessoa atualizaPessoa(@Valid @RequestBody Pessoa novaPessoa, @PathVariable Long id) {
		return repository.findById(id)
				.map(pessoa -> {
					pessoa.atualiza(novaPessoa);
					return repository.save(pessoa);
				})
				.orElseGet(() -> {
					return repository.save(novaPessoa);
				});
	}

	@DeleteMapping("/pessoas/{id}")
	void exclui(@PathVariable Long id) {
		repository.deleteById(id);
	}

}