package com.desafio.concrete.cadastro.error;

import org.springframework.http.HttpStatus;

public class NegocioException extends Exception {

	private HttpStatus status;
	
	public NegocioException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
