package com.programar.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.programar.workshopmongo.domain.User;
import com.programar.workshopmongo.dto.UserDTO;
import com.programar.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();	
		
		//expressao lambda que transforma uma lista de usuarios em uma lista de usuarios dto 
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		//retorna no corpo da resposta a lista de usuarios criada
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {	
		User obj = service.findById(id);
		
		//retorna no corpo da resposta a lista de usuarios criada
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {	
		//usuario recebe um objeto de usuario dto
		User obj = service.fromDto(objDto);
		
		//servico insere novo objeto usuario
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//retorna codigo 201 - codigo http quando criamos novo recurso 
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {	
		//usuario recebe o objeto usuario a ser atualizado
		User obj = service.fromDto(objDto);
		
		//forca o id informado a ser igual ao do objeto
		obj.setId(id);
		
		//servico altera objeto usuario
		obj = service.update(obj);
		
		//retorna mensagem de sucesso
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {	
		service.delete(id);
		
		//retorna no corpo da resposta a lista de usuarios criada
		return ResponseEntity.noContent().build();
	}
}
