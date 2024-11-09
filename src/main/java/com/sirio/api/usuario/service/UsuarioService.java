package com.sirio.api.usuario.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirio.api.configs.SecurityUtils;
import com.sirio.api.usuario.dto.UsuarioDTO;
import com.sirio.api.usuario.model.StatusRegistro;
import com.sirio.api.usuario.model.Usuario;
import com.sirio.api.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public List<Usuario> findAllActive() {
		return usuarioRepository.findAllAtivosByStatus(StatusRegistro.ATIVO);
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
	}

	public Usuario findByCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o CPF: " + cpf));
	}

	public Usuario create(UsuarioDTO usuarioDTO) {
		String usuarioLogado = SecurityUtils.getAuthenticatedUsername();

		Usuario usuario = usuarioDTO.create(usuarioLogado);

		return usuarioRepository.save(usuario);
	}

	public Usuario update(Long id, UsuarioDTO usuarioAtualizado) {
		String usuarioLogado = SecurityUtils.getAuthenticatedUsername();

		Usuario usuarioExistente = findById(id);
		
		if(!usuarioExistente.getCpf().equals(usuarioAtualizado.getCpf()))
			throw new RuntimeException("Não é possivel alterar o CPF");

		usuarioExistente.setDataAtualizacao(LocalDateTime.now());
		usuarioExistente.setUsuarioAtualizacao(usuarioLogado);
		
		usuarioExistente = usuarioAtualizado.parse(usuarioExistente);

		return usuarioRepository.save(usuarioExistente);
	}

	public void delete(Long id) {
		String usuarioLogado = SecurityUtils.getAuthenticatedUsername();

		Usuario usuario = findById(id);
		
		if(usuario.getStatus().equals(StatusRegistro.REMOVIDO))
			throw new RuntimeException("Este usuário ja foi removido!");
		
		usuario.setUsuarioRemocao(usuarioLogado);
		usuario.setStatus(StatusRegistro.REMOVIDO);
		usuario.setDataRemocao(java.time.LocalDateTime.now());
		
		usuarioRepository.save(usuario);
	}
}
