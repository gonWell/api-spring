package com.sirio.api.usuario.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Endereco {

	public Endereco() {

	}

	@JsonProperty(defaultValue = "Bonfiglio")
	private String rua;

	@JsonProperty(defaultValue = "15")
	private Integer numero;

	@JsonProperty(defaultValue = "")
	private String complemento;

	@JsonProperty(defaultValue = "Bela Vista")
	private String bairro;

	@JsonProperty(defaultValue = "São Paulo")
	private String cidade;

	@JsonProperty(defaultValue = "SP")
	private String estado;

	@JsonProperty(defaultValue = "13188000")
	private String cep;
}
