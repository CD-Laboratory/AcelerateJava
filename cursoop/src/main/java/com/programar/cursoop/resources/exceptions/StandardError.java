package com.programar.cursoop.resources.exceptions;

import java.io.Serializable;

//Auxilia classes Exceptions
public class StandardError implements Serializable{
	
   //Atributos
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timeStamp;
	
	//Construtores
	public StandardError(Integer status, String msg, Long timeStamp){
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	//Getters e Setters
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
