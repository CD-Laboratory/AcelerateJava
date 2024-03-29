package com.programar.cursoop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.programar.cursoop.domain.Categoria;
import com.programar.cursoop.dto.CategoriaDTO;
import com.programar.cursoop.repositories.CategoriaRepository;
import com.programar.cursoop.services.exceptions.DataIntegrityException;
import com.programar.cursoop.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository rep;
	
	// buscar categoria
	public Categoria find(Integer id) {
		// acessa o banco de dados, buscando uma categoria pelo id
		Optional<Categoria> obj = rep.findById(id);
		
		//retornando a categoria e produto, com tratamento de excecao para caso a categoria buscada não exista
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	// inserir na tabela categoria
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return rep.save(obj);
	}
	
	// atualizar uma categoria da tabela categoria
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return rep.save(newObj);
	}
	
	//deletar categoria
	public void delete(Integer id) {
		find(id);
		
		try {
			rep.deleteById(id);
		} catch (DataIntegrityViolationException e) {
		
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	// listar categoria
	public List<Categoria> findAll(){
		return rep.findAll();
	}
	
	// retornar uma pagina de categoria 
	// ele recebera o numero da pagina - page, o total de linhas por pagina - linesPage, o objeto de ordenacao - orderBy
	// e a direcao - direction, que nada mais é que informar se sera apresentado em forma crescente ou descencente
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return rep.findAll(pageRequest);
	}
	
	// converter categoriaDTO para Categoria
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	//atualizar dados dos campos alterados da categoria
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
