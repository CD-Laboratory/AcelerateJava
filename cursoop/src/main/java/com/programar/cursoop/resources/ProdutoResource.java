package com.programar.cursoop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.programar.cursoop.domain.Produto;
import com.programar.cursoop.dto.ProdutoDTO;
import com.programar.cursoop.resources.utils.URL;
import com.programar.cursoop.services.ProdutoService;

@RestController 
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	//o controlador acessa o servico
	@Autowired
	private ProdutoService service;
  	
	// este metodo tera o end-point /pedidos/id que o usuario informar
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find (@PathVariable Integer id) {
		
		// o servico acessa os dados, o repository atraves do metodo buscar
		Produto obj = service.find(id);
		
		// retorna a mensagem positiva 
		return ResponseEntity.ok().body(obj);
	}
	
	// paginar produto
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage (
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {		
		
		String nomeDecoded = URL.decodeParam(nome);
		
		List<Integer> ids = URL.decodeIntList(categorias);
		
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, direction, orderBy);
		
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
}
