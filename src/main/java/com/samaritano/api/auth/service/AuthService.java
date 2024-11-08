package com.samaritano.api.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samaritano.api.usuario.model.Usuario;
import com.samaritano.api.usuario.repository.UsuarioRepository;

@Service
public class AuthService {
	

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> loginByCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}

}
