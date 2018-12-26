package com.desafio.concrete.cadastro;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafio.concrete.cadastro.error.NegocioException;
import com.desafio.concrete.cadastro.model.Telefone;
import com.desafio.concrete.cadastro.model.Usuario;
import com.desafio.concrete.cadastro.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroApplicationTests {

	@Autowired
	private UsuarioService service;
	
	@Test
	public void testSaveUserSucess() throws NegocioException {
		Usuario usuario = criarUsuario();
		usuario = service.salvar(usuario);
		
		assertThat(usuario.getId(), equalTo(1L));
		assertThat(usuario.getNome(), equalTo("João da Silva"));
		assertThat(usuario.getEmail(), equalTo("joao@silva.org"));
		assertThat(usuario.getSenha(), equalTo("hunter2"));
		
		Telefone telefone = usuario.getTelefones().get(0);
		assertThat(telefone.getId(), equalTo(1L));
		assertThat(telefone.getNumero(), equalTo(987654321));
		assertThat(telefone.getDdd(), equalTo(21));
	}
	
	@Test(expected = NegocioException.class)
	public void testSaveUserError() throws NegocioException {
		Usuario usuario1 = criarUsuario();
		service.salvar(usuario1);
		service.salvar(usuario1);
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("João da Silva");
		usuario.setEmail("joao@silva.org");
		usuario.setSenha("hunter2");
		
		List<Telefone> telefones = new ArrayList<>();
		Telefone telefone = new Telefone();
		telefone.setUsuario(usuario);
		telefone.setNumero(987654321);
		telefone.setDdd(21);
		telefones.add(telefone);
		
		usuario.setTelefones(telefones);
		
		return usuario;
	}

}

