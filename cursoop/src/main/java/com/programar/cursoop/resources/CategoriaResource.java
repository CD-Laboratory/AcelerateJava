package com.programar.cursoop.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.programar.cursoop.domain.Categoria;
import com.programar.cursoop.dto.CategoriaDTO;
import com.programar.cursoop.services.CategoriaService;

@RestController 
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	//o controlador acessa o servico
	@Autowired
	private CategoriaService service;
  	
	// este metodo tera o end-point /categorias/id que o usuario informar
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find (@PathVariable Integer id) {
		// o servico acessa os dados, o repository atraves do metodo buscar
		Categoria obj = service.find(id);
		
		// retorna a mensagem positiva 
		return ResponseEntity.ok().body(obj);
	}
	
	//ira receber uma categoria e inserir no banco de dados
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert (@Valid @RequestBody CategoriaDTO objDTO){
		
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		
		//chamada que pega a uri do novo recrso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//atualiza categoria existente
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		
		Categoria obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		//retorna resposta sem conteuno, somente o codigo da resposta
		return ResponseEntity.noContent().build();
	}
	
	// deletar categoria
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		
		//retorna resposta sem conteuno, somente o codigo da resposta
		return ResponseEntity.noContent().build();
	}
	
	// listar categoria
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll () {
		// o servico acessa os dados, o repository atraves do metodo buscar
		List<Categoria> list = service.findAll();
		
		// recebe uma lista de categorias pegando somente os atributos definidos pelo categoriaDTO
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		// retorna a mensagem positiva 
		return ResponseEntity.ok().body(listDTO);
	}

	// paginar categoria
		@RequestMapping(value = "/page", method=RequestMethod.GET)
		public ResponseEntity<Page<CategoriaDTO>> findPage (
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction,
				@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {		
			
			Page<Categoria> list = service.findPage(page, linesPerPage, direction, orderBy);
			
			Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
			
			return ResponseEntity.ok().body(listDTO);
		}
		
}


