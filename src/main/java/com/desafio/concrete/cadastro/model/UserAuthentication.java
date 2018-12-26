package com.desafio.concrete.cadastro.model;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication implements Authentication {

	private Usuario usuario;
	private boolean autenticado;
	private HttpStatus status;
	
	public UserAuthentication(Usuario usuario, HttpStatus status) {
		this.usuario = usuario;
		this.status = status;
	}
	
	@Override
	public String getName() {
		return usuario != null ? usuario.getEmail() : null;
	}

	@Override
	public Object getCredentials() {
		return usuario != null ? usuario.getSenha() : null;
	}
	
	@Override
	public Object getDetails() {
		return usuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return usuario != null ? usuario : null;
	}

	@Override
	public boolean isAuthenticated() {
		return this.autenticado;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.autenticado = isAuthenticated;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
