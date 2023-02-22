package br.com.attornatus.vaga.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.vaga.model.Endereco;
import br.com.attornatus.vaga.model.Pessoa;
import br.com.attornatus.vaga.repository.EnderecoDao;
import br.com.attornatus.vaga.repository.PessoaDao;

@RestController
public class EnderecoController {
	@Autowired
	private PessoaDao pessoaDao;
	@Autowired
	private EnderecoDao enderecoDao;
	
	
	@PostMapping(value = "/salvarendereco/{pessoa_id}")
    @ResponseBody
    public String salvarEnderecoTeste(String result,@PathVariable(value = "pessoa_id")Long idPessoa){
    	Optional<Pessoa> pessoaOp  =  pessoaDao.findById(idPessoa);
    	Pessoa pessoa = pessoaOp.get();
    	Endereco enderecoUm = new Endereco();
    	enderecoUm.setCep(3727322);
    	enderecoUm.setCidade("rio do sul");
    	enderecoUm.setEndereçoPrincipal(true);
    	enderecoUm.setLogradouro("rua sebastiao");
    	enderecoUm.setNumero(510);
    	enderecoUm.setPessoa(pessoa);  	
    	
    enderecoDao.save(enderecoUm);
    return "salvo";
    	
    }
   
	@PostMapping(value = "/salvarenderecometodo")
    @ResponseBody
    public ResponseEntity<Endereco>salvar(@RequestBody Endereco endereco ,@RequestParam(name = "idPessoa") Long idPessoa){
	Optional<Pessoa> pessoaBusca = pessoaDao.findById(idPessoa);
	Pessoa pessoa =  pessoaBusca.get();
	 endereco.setPessoa(pessoa);
    Endereco enderecoSalvar =   enderecoDao.save(endereco);
    return new ResponseEntity<Endereco>(enderecoSalvar , HttpStatus.CREATED);
    	
    }
	
	
}
