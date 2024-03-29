package com.programar.cursoop.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.programar.cursoop.domain.Pedido;
import com.programar.cursoop.services.PedidoService;

@RestController 
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	//o controlador acessa o servico
	@Autowired
	private PedidoService service;
  	
	// este metodo tera o end-point /pedidos/id que o usuario informar
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> find (@PathVariable Integer id) {
		// o servico acessa os dados, o repository atraves do metodo buscar
		Pedido obj = service.find(id);
		
		// retorna a mensagem positiva 
		return ResponseEntity.ok().body(obj);
	}
	
	//ira receber um pedido e inserir no banco de dados
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert (@Valid @RequestBody Pedido obj){
	
		obj = service.insert(obj);
		
		//chamada que pega a uri do novo recrso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
