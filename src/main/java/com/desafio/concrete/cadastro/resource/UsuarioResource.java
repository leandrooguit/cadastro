package com.desafio.concrete.cadastro.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.concrete.cadastro.error.NegocioException;
import com.desafio.concrete.cadastro.mapping.Mapeador;
import com.desafio.concrete.cadastro.model.Usuario;
import com.desafio.concrete.cadastro.service.UsuarioService;

@RestController
@RequestMapping
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private Mapeador mapeador;
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario) throws NegocioException {
		usuario = service.salvar(usuario);
		UsuarioDto dto = mapeador.getInstancia().map(usuario, UsuarioDto.class);
		return new ResponseEntity<UsuarioDto>(dto, HttpStatus.CREATED);
	}
	
	@GetMapping("/logado/{login}/{statusCode}")
	public ResponseEntity<UsuarioDto> logado(@PathVariable("login") String login, 
			@PathVariable("statusCode") int statusCode) 
			throws NegocioException {
		Usuario usuario = service.atualizar(login, statusCode);
		UsuarioDto dto = mapeador.getInstancia().map(usuario, UsuarioDto.class);
		return new ResponseEntity<UsuarioDto>(dto, HttpStatus.OK);
	}
	
	
}
