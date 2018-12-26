package com.desafio.concrete.cadastro.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafio.concrete.cadastro.error.NegocioException;
import com.desafio.concrete.cadastro.model.Telefone;
import com.desafio.concrete.cadastro.model.Usuario;
import com.desafio.concrete.cadastro.repository.Usuarios;
import com.desafio.concrete.cadastro.util.JwtUtil;

@Service
public class UsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	public Usuario salvar(Usuario usuario) throws NegocioException {
		Optional<Usuario> emailExiste = usuarios.findByEmail(usuario.getEmail());
		
		if (emailExiste.isPresent()) {
			throw new NegocioException("e-mail.existente", HttpStatus.BAD_REQUEST);
		}
		
		usuario.setDataCriacao(LocalDate.now());
		usuario.setDataUltimaAtualizacao(LocalDate.now());
		usuario.setDataUltimoLogin(LocalDateTime.now());
		usuario.setSenha(usuario.getSenha());
		String token = JwtUtil.generateToken(usuario.getEmail());
		usuario.setToken(token);
		
		for (Telefone telefone : usuario.getTelefones()) {
			telefone.setUsuario(usuario);
		}
		
		return usuarios.saveAndFlush(usuario);
	}
	
	public Usuario atualizar(String login, int statusCode) throws NegocioException {
		HttpStatus status = HttpStatus.valueOf(statusCode);
		
		if (!HttpStatus.OK.equals(status)) {
			throw new NegocioException("usuario_e_ou_invalidados", status);
		}
		
		Optional<Usuario> usuario = usuarios.findByEmail(login);
		usuario.get().setDataUltimoLogin(LocalDateTime.now());
		return usuarios.saveAndFlush(usuario.get());
	}
	
}
