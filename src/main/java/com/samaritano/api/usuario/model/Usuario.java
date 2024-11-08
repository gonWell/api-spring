package com.samaritano.api.usuario.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
	
	public Usuario() {
		dataCriacao = LocalDateTime.now();
		status = StatusRegistro.ATIVO;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cpf;

	private String nome;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDateTime dataNascimento;

	@Embedded
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	private StatusRegistro status;

	@Column(name = "data_criacao", nullable = false, updatable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "usuario_criacao", nullable = false, updatable = false)
	private String usuarioCriacao;

	@Column(name = "data_atualizacao")
	private LocalDateTime dataAtualizacao;

	@Column(name = "usuario_atualizacao")
	private String usuarioAtualizacao;

	@Column(name = "data_remocao")
	private LocalDateTime dataRemocao;

	@Column(name = "usuario_remocao")
	private String usuarioRemocao;
}
