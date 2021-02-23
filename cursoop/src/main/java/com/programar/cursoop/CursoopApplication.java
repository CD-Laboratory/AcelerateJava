package com.programar.cursoop;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.programar.cursoop.domain.Categoria;
import com.programar.cursoop.domain.Produto;
import com.programar.cursoop.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoopApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoopApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
