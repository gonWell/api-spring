package com.samaritano.api.usuario.model;

import lombok.Data;

@Data
public class Endereco {
	
	public Endereco() {
		
	}

    private String rua;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;
}
