package br.com.attornatus.vaga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.attornatus.vaga.model.Pessoa;

@Repository
@Transactional
public interface PessoaDao extends JpaRepository<Pessoa, Long> {

	@Query(value = "select u from Pessoa u where upper(trim(u.nome)) like %?1%")
	List<Pessoa> buscaPorNome(String nome);
}
