package com.programar.cursoop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programar.cursoop.domain.Categoria;
import com.programar.cursoop.services.CategoriaService;

@RestController 
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	//o controlador acessa o servico
	@Autowired
	private CategoriaService service;
  	
	// este metodo tera o end-point /categorias/id que o usuario informar
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> findCategorias (@PathVariable Integer id) {
		
		// o servico acessa os dados, o repository atraves do metodo buscar
		Categoria obj = service.buscar(id);
		
		// retorna a mensagem positiva 
		return ResponseEntity.ok().body(obj);
	}
}
