package br.com.attornatus.vaga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.attornatus.vaga.model.Endereco;

@Repository
@Transactional
public interface EnderecoDao extends JpaRepository<Endereco, Long>{

}
