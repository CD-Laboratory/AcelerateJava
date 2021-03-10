package com.programar.cursoop.domain;

import javax.persistence.Entity;

import com.programar.cursoop.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{

	//Atributos
	private static final long serialVersionUID = 1L;
	private Integer numeroDeParcelas;
	
	//Construtores
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
	}

	//Getters and Setters
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}