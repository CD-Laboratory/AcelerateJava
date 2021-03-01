package com.programar.cursoop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.programar.cursoop.domain.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
}
