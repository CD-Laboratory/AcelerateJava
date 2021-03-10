package com.programar.cursoop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programar.cursoop.domain.Cliente;
import com.programar.cursoop.repositories.ClienteRepository;
import com.programar.cursoop.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository rep;
	
	// buscar cliente
	public Cliente find(Integer id) {
		// acessa o banco de dados, buscando um cliente pelo id
		Optional<Cliente> obj = rep.findById(id);
		
		//retornando o cliente, com tratamento de excecao para caso i cliente buscado não exista
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
