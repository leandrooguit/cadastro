package com.desafio.concrete.cadastro.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class AutenticacaoException extends AuthenticationException {

	private HttpStatus status;
	
	public AutenticacaoException(String msg, HttpStatus status) {
		super(msg);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
