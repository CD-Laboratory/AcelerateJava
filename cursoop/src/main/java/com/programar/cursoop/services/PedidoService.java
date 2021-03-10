package com.programar.cursoop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programar.cursoop.domain.Pedido;
import com.programar.cursoop.repositories.PedidoRepository;
import com.programar.cursoop.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository rep;
	
	// buscar pedido
	public Pedido find(Integer id) {
		// acessa o banco de dados, buscando um pedido pelo id
		Optional<Pedido> obj = rep.findById(id);
		
		//retornando o pedido, com tratamento de excecao para caso o pedido buscado não exista
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
