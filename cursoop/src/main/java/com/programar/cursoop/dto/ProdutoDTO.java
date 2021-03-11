package com.programar.cursoop.dto;

import java.io.Serializable;

import com.programar.cursoop.domain.Produto;

public class ProdutoDTO  implements Serializable {

	//Atributos
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private double preco;
	
	//Construtores
	public ProdutoDTO() {}
	
	public ProdutoDTO(Produto obj) {
	   id = obj.getId();
	   nome = obj.getNome();
	   preco = obj.getPreco();
	}



	//Geters And Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
