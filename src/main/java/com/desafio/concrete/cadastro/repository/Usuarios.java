package com.desafio.concrete.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafio.concrete.cadastro.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
	@Query("select usuario from Usuario as usuario where usuario.email=:email and usuario.senha<>:senha")
	Optional<Usuario> existeLoginEhSenhaNao(@Param("email") String email, @Param("senha") String senha);
	
}
