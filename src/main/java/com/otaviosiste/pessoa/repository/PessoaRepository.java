package com.otaviosiste.pessoa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.otaviosiste.pessoa.entidade.Pessoa;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa,Long>{

}
