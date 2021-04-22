package com.programar.workshopmongo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.programar.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	/*dentro da query eu informo o filtro que vou usar na busca
	 * o parameto - ?0 - significa que ira pegar o primeiro parametro vindo no metodo, no casso o text
	 * e as opcoes - o i significa que serao ignoradas letras maiusculas e minusculas
	 * */
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	// o ignore case na frente do metodo informa que o tamanho da string recebida como parametro pelo metodo sera ignorada
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} }, { $or: [ { 'title' : { $regex: ?0, $options: 'i' } }, "
			+ "{ 'body' : { $regex: ?0, $options: 'i' } }, " 
			+ "{ 'comments.text' : { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
