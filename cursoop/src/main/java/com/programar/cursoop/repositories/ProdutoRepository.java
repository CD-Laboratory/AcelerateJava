package com.programar.cursoop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.programar.cursoop.domain.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
}
