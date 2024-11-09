package com.sirio.api.usuario.dto;

import java.time.LocalDateTime;

import com.sirio.api.usuario.model.Endereco;
import com.sirio.api.usuario.model.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class UsuarioDTO {

	public UsuarioDTO() {

	}

	private String cpf;

	private String nome;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDateTime dataNascimento;

	@Embedded
	private Endereco endereco;
	
	public Usuario parse(Usuario usuario) {
		usuario.setCpf(cpf);
		usuario.setNome(nome);
		usuario.setDataNascimento(dataNascimento);
		usuario.setEndereco(endereco);
		
		return usuario;
	}
	
	public Usuario create(String cpfUsuario)
	{
		Usuario usuario = parse(new Usuario());
		
		usuario.setUsuarioCriacao(cpfUsuario);
		
		return usuario;
	}

}
