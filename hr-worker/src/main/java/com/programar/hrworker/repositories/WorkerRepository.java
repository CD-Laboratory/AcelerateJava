package com.programar.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programar.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

	
}
