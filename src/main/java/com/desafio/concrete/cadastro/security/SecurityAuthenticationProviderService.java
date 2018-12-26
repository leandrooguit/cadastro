package com.desafio.concrete.cadastro.security;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.desafio.concrete.cadastro.model.UserAuthentication;
import com.desafio.concrete.cadastro.model.Usuario;
import com.desafio.concrete.cadastro.repository.Usuarios;

@Component
public class SecurityAuthenticationProviderService implements AuthenticationProvider {

	@Autowired
	private Usuarios usuarios;
	
	@Override
	public Authentication authenticate(Authentication auth) 
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
		
		Optional<Usuario> usuario = usuarios.findByEmail(token.getName());
		Optional<Usuario> existeLoginEhSenhaNao = usuarios.existeLoginEhSenhaNao(token.getName(), token.getCredentials().toString());
		
		if (!usuario.isPresent() || existeLoginEhSenhaNao.isPresent()) {
			return new UserAuthentication(null, HttpStatus.UNAUTHORIZED);
		}
		
		usuario.get().setDataUltimoLogin(LocalDateTime.now());
		Usuario usuarioBase = usuarios.saveAndFlush(usuario.get());
		
		UserAuthentication authentication = new UserAuthentication(usuarioBase, HttpStatus.OK);
		authentication.setAuthenticated(usuario != null);
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

}
