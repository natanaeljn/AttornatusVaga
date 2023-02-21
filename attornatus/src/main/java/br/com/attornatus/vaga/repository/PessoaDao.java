package br.com.attornatus.vaga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.attornatus.vaga.model.Pessoa;

@Repository
@Transactional
public interface PessoaDao extends JpaRepository<Pessoa, Long>{

}
