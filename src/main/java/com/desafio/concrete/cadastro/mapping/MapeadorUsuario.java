package com.desafio.concrete.cadastro.mapping;

import org.springframework.stereotype.Service;

import com.desafio.concrete.cadastro.model.Usuario;
import com.desafio.concrete.cadastro.resource.UsuarioDto;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

@Service
@Mapear
public class MapeadorUsuario extends CustomMapper<Usuario, UsuarioDto> {

	@Override
	public void mapAtoB(Usuario usuario, UsuarioDto dto, MappingContext context) {
		dto.setId(usuario.getId());
		dto.setDtCriacao(usuario.getDataCriacao());
		dto.setDtUltimaAtualizacao(usuario.getDataUltimaAtualizacao());
		dto.setDtUltimoLogin(usuario.getDataUltimoLogin());
		dto.setToken(usuario.getToken());
	}

	@Override
	public void mapBtoA(UsuarioDto dto, Usuario usuario, MappingContext context) {
		
	}

}
