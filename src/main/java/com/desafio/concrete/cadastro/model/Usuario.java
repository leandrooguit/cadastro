package com.desafio.concrete.cadastro.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "USUARIO")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank(message = "usuario-1")
	@Column(name = "NOME")
	private String nome;
	
	@NotBlank(message = "usuario-2")
	@Column(name = "EMAIL")
	private String email;
	
	@NotBlank(message = "usuario-3")
	@Column(name = "SENHA")
	private String senha;
	
	@Column(name = "DT_CRIACAO")
	private LocalDate dataCriacao;
	
	@Column(name = "DT_ULTIMA_ATUALIZACAO")
	private LocalDate dataUltimaAtualizacao;
	
	@Column(name = "DT_ULTIMA_LOGIN")
	private LocalDateTime dataUltimoLogin;
	
	@Column(name = "TOKEN")
	private String token;
	
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<Telefone> telefones;
	
}
