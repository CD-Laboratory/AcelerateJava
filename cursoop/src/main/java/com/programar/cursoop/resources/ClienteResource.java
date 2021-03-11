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

import com.programar.cursoop.domain.Cliente;
import com.programar.cursoop.dto.ClienteDTO;
import com.programar.cursoop.dto.ClienteNewDTO;
import com.programar.cursoop.services.ClienteService;

@RestController 
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	//o controlador acessa o servico
	@Autowired
	private ClienteService service;
  	
	// este metodo tera o end-point /Clientes/id que o usuario informar
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find (@PathVariable Integer id) {
		// o servico acessa os dados, o repository atraves do metodo buscar
		Cliente obj = service.find(id);
		
		// retorna a mensagem positiva 
		return ResponseEntity.ok().body(obj);
	}
	
	//ira receber uma cliente e inserir no banco de dados
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert (@Valid @RequestBody ClienteNewDTO objDTO){
		
		Cliente obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		
		//chamada que pega a uri do novo recrso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//atualiza cliente existente
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		
		Cliente obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		//retorna resposta sem conteuno, somente o codigo da resposta
		return ResponseEntity.noContent().build();
	}
	
	// deletar cliente
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		
		//retorna resposta sem conteuno, somente o codigo da resposta
		return ResponseEntity.noContent().build();
	}
	
	// listar cliente
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll () {
		// o servico acessa os dados, o repository atraves do metodo buscar
		List<Cliente> list = service.findAll();
		
		// recebe uma lista de clientes pegando somente os atributos definidos pelo clienteDTO
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		// retorna a mensagem positiva 
		return ResponseEntity.ok().body(listDTO);
	}

	// paginar cliente
		@RequestMapping(value = "/page", method=RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findPage (
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction,
				@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {		
			
			Page<Cliente> list = service.findPage(page, linesPerPage, direction, orderBy);
			
			Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
			
			return ResponseEntity.ok().body(listDTO);
		}
}
