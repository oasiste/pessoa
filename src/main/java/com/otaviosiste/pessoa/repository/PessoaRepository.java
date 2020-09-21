package com.otaviosiste.pessoa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.otaviosiste.pessoa.entidade.Pessoa;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa,Long>{
	
	
	List<Pessoa> findAllByNome(String nome, Pageable pageable);

}
