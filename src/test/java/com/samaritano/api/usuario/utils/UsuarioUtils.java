package com.samaritano.api.usuario.utils;

import java.time.LocalDateTime;
import java.util.Random;

import com.samaritano.api.usuario.dto.UsuarioDTO;
import com.samaritano.api.usuario.model.Endereco;
import com.samaritano.api.usuario.model.StatusRegistro;
import com.samaritano.api.usuario.model.Usuario;

public class UsuarioUtils {

	
	public static Usuario create() {
		Usuario usuario = new Usuario();

		usuario.setId(null);		
		usuario.setCpf("12345678901");
		usuario.setNome("Usuario teste");
		usuario.setDataNascimento(LocalDateTime.now());
		usuario.setEndereco(createEndereco());

		return usuario;
	}
	
	public static UsuarioDTO createDTO() {
		UsuarioDTO usuario = new UsuarioDTO();
	
		usuario.setCpf("12345678901");
		usuario.setNome("Usuario teste");
		usuario.setDataNascimento(LocalDateTime.now());
		usuario.setEndereco(createEndereco());

		return usuario;
	}
	
	public static Usuario create(String cpf, String nome) {
		Usuario usuario = create();
		
		usuario.setCpf(cpf);
		usuario.setNome(nome);
		
		return usuario;
	}
	
	public static UsuarioDTO createDTO(String cpf, String nome) {
		UsuarioDTO usuario = createDTO();
		
		usuario.setCpf(cpf);
		usuario.setNome(nome);
		
		return usuario;
	}
	
	
	public static Usuario create(String cpf, String nome, StatusRegistro status) {
		Usuario usuario = create(cpf, nome);

		usuario.setStatus(status);
		
		return usuario;
	}
	
	public static UsuarioDTO createDTO(String cpf, String nome, StatusRegistro status) {
		UsuarioDTO usuario = createDTO(cpf, nome);
		
		return usuario;
	}
	
	public static Usuario createComIdRandom(String cpf, String nome, StatusRegistro status) {
		Usuario usuario = create(cpf, nome);

		Random rdm = new Random();
		
		usuario.setId(rdm.nextLong());
		
		usuario.setStatus(status);
		
		return usuario;
	}
	
	public static Usuario createComId(Long id, String cpf, String nome, StatusRegistro status) {
		Usuario usuario = create(cpf, nome);

		usuario.setId(id);		
		usuario.setStatus(status);
		
		return usuario;
	}
	
	
	public static Endereco createEndereco() {
		Endereco endereco = new Endereco();
		
		endereco.setEstado("SP");
		endereco.setCidade("São Paulo");
		endereco.setBairro("Bela Vista");
		endereco.setComplemento("");
		endereco.setNumero(100);
		endereco.setRua("Bonfiglio");
		
		return endereco;
	}
}
