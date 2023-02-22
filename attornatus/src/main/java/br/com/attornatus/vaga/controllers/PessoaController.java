package br.com.attornatus.vaga.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.vaga.model.Endereco;
import br.com.attornatus.vaga.model.Pessoa;
import br.com.attornatus.vaga.repository.EnderecoDao;
import br.com.attornatus.vaga.repository.PessoaDao;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class PessoaController {

	@Autowired
	private PessoaDao pessoaDao;
	@Autowired
	private EnderecoDao enderecoDao;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Hello " + name + "!";
	}

	@RequestMapping(value = "/salvar/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String testeSalvar(@PathVariable String name) {
		Pessoa pessoa = new Pessoa();

		pessoa.setNome(name);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		pessoa.setDataNascimento(LocalDate.parse("21/02/2022", formatter));
		pessoaDao.save(pessoa);

		return "Usuario " + name + ", salvo com sucesso";
	}

	/* lista as pessoas com seus respectivos enderecos */
	@GetMapping(value = "/listaPessoas")
	@ResponseBody
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		List<Pessoa> pessoas = pessoaDao.findAll();

		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}

	/* busca por Id */
	@GetMapping(value = "/buscapessoaid")
	@ResponseBody
	public ResponseEntity<Pessoa> consultarPessoaId(@RequestParam(name = "idPessoa") Long idPessoa) {
		Pessoa pessoaId = pessoaDao.findById(idPessoa).get();
		return new ResponseEntity<Pessoa>(pessoaId, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar")
	@ResponseBody
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
		Pessoa usuarioSalvar = pessoaDao.save(pessoa);
		return new ResponseEntity<Pessoa>(usuarioSalvar, HttpStatus.CREATED);

	}

	/* metodo para editar e atualizar */
	@PutMapping(value = "/atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Pessoa pessoa) {
		if (pessoa.getId() == null) {
			return new ResponseEntity<String>("Id nao foi informado", HttpStatus.OK);
		}

		Pessoa pessoaSalvar = pessoaDao.save(pessoa);
		return new ResponseEntity<Pessoa>(pessoaSalvar, HttpStatus.OK);

	}

	@DeleteMapping(value = "/deletar")
	@ResponseBody
	public ResponseEntity<String> deletar(@RequestParam Long idPessoa) {
		pessoaDao.deleteById(idPessoa);
		return new ResponseEntity<String>("pessoa deletada", HttpStatus.OK);

	}

	@GetMapping(value = "/buscapessoanome")
	@ResponseBody
	public ResponseEntity<List<Pessoa>> buscaUserNome(@RequestParam(name = "nome") String nome) {
		List<Pessoa> lista = pessoaDao.buscaPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Pessoa>>(lista, HttpStatus.OK);

	}

}
