package com.desafio.concrete.cadastro.resource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsuarioDto {

	private Long id;
	private LocalDate dtCriacao;
	private LocalDate dtUltimaAtualizacao;
	private LocalDateTime dtUltimoLogin;
	private String token;
	
}
