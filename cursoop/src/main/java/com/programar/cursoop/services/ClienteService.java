package com.programar.cursoop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programar.cursoop.domain.Cidade;
import com.programar.cursoop.domain.Cliente;
import com.programar.cursoop.domain.Endereco;
import com.programar.cursoop.domain.enums.TipoCliente;
import com.programar.cursoop.dto.ClienteDTO;
import com.programar.cursoop.dto.ClienteNewDTO;
import com.programar.cursoop.repositories.ClienteRepository;
import com.programar.cursoop.repositories.EnderecoRepository;
import com.programar.cursoop.services.exceptions.DataIntegrityException;
import com.programar.cursoop.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository rep;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	// buscar cliente
	public Cliente find(Integer id) {
		// acessa o banco de dados, buscando um cliente pelo id
		Optional<Cliente> obj = rep.findById(id);
		
		//retornando o cliente, com tratamento de excecao para caso i cliente buscado não exista
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	// inserir na tabela cliente
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = rep.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	// atualizar uma cliente da tabela cliente
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return rep.save(newObj);
	}
	
	//deletar cliente
	public void delete(Integer id) {
		find(id);
		
		try {
			rep.deleteById(id);
		} catch (DataIntegrityViolationException e) {
		
			throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionados");
		}
	}
	
	// listar cliente
	public List<Cliente> findAll(){
		return rep.findAll();
	}
	
	// retornar uma pagina de cliente 
	// ele recebera o numero da pagina - page, o total de linhas por pagina - linesPage, o objeto de ordenacao - orderBy
	// e a direcao - direction, que nada mais é que informar se sera apresentado em forma crescente ou descencente
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return rep.findAll(pageRequest);
	}
	
	// converter clienteDTO para Cliente - usado para pesquisar cliente
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	// converter clienteNewDTO para Cliente - usado para inserir cliente
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOucnpj(),
				TipoCliente.toEnum(objDTO.getTipo()));
		
		//Aqui nao usamos a cidadeRepository, porque estamos constatando que a cidade ja existe no banco de dados
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), 
				objDTO.getBairro(), objDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
	
	//atualizar dados dos campos alterados do cliente
	private void updateData(Cliente newObj, Cliente obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
