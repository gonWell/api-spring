package com.sirio.api.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirio.api.usuario.model.StatusRegistro;
import com.sirio.api.usuario.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCpf(String cpf);
    
    List<Usuario> findAllAtivosByStatus(StatusRegistro status);
}
