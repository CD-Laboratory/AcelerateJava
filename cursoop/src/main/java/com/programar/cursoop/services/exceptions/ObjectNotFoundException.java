package com.programar.cursoop.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
        //passando a mensagem 
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
        //passando a mensagem  e a causa do erro para a super classe
		super(msg, cause);
	}

}
