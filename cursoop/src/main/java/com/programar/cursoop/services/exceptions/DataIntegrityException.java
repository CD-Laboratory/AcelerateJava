package com.programar.cursoop.services.exceptions;

public class DataIntegrityException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
        //passando a mensagem 
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
        //passando a mensagem  e a causa do erro para a super classe
		super(msg, cause);
	}

}
