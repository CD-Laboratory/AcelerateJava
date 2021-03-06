package com.programar.cursoop.domain.enums;


public enum TipoCliente {

	//tipos enums
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOCAJURIDICA(2, "Pessoa Juridíca");
	
	//atributos
	private int cod;
	private String descricao;
	
	// construtor de enum é private
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	//Getters
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
		   return null;	
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
	
}
