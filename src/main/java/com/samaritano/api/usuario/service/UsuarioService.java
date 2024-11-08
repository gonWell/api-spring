package com.samaritano.api.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samaritano.api.configs.SecurityUtils;
import com.samaritano.api.usuario.model.StatusRegistro;
import com.samaritano.api.usuario.model.Usuario;
import com.samaritano.api.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
	}

	public Usuario findByCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o CPF: " + cpf));
	}

	public Usuario create(Usuario usuario) {
		String usuarioLogado = SecurityUtils.getAuthenticatedUsername();

		usuario.setUsuarioCriacao(usuarioLogado);

		return usuarioRepository.save(usuario);
	}

	public Usuario update(Long id, Usuario usuarioAtualizado) {
		String usuarioLogado = SecurityUtils.getAuthenticatedUsername();

		Usuario usuarioExistente = findById(id);

		usuarioExistente.setUsuarioAtualizacao(usuarioLogado);
		usuarioExistente.setNome(usuarioAtualizado.getNome());
		usuarioExistente.setCpf(usuarioAtualizado.getCpf());
		usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
		usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
		usuarioExistente.setStatus(usuarioAtualizado.getStatus());

		return usuarioRepository.save(usuarioExistente);
	}

	public void delete(Long id) {
		String usuarioLogado = SecurityUtils.getAuthenticatedUsername();

		Usuario usuario = findById(id);
		
		usuario.setUsuarioRemocao(usuarioLogado);
		usuario.setStatus(StatusRegistro.REMOVIDO);
		usuario.setDataRemocao(java.time.LocalDateTime.now());
		
		usuarioRepository.save(usuario);
	}
}
