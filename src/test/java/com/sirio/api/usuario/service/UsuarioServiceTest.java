package com.sirio.api.usuario.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sirio.api.usuario.model.StatusRegistro;
import com.sirio.api.usuario.model.Usuario;
import com.sirio.api.usuario.repository.UsuarioRepository;
import com.sirio.api.usuario.service.UsuarioService;
import com.sirio.api.usuario.utils.UsuarioUtils;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Usuario> usuarios = Arrays.asList(
                UsuarioUtils.createComId(1L, "12345678901", "João", StatusRegistro.ATIVO),
                UsuarioUtils.createComId(2L, "98765432109", "Maria", StatusRegistro.ATIVO)
        );

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("João", result.get(0).getNome());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testFindAllActive() {
        List<Usuario> usuariosAtivos = Arrays.asList(
                UsuarioUtils.createComId(1L, "12345678901", "João", StatusRegistro.ATIVO)
        );

        when(usuarioRepository.findAllAtivosByStatus(StatusRegistro.ATIVO)).thenReturn(usuariosAtivos);

        List<Usuario> result = usuarioService.findAllActive();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(StatusRegistro.ATIVO, result.get(0).getStatus());
        verify(usuarioRepository, times(1)).findAllAtivosByStatus(StatusRegistro.ATIVO);
    }

    @Test
    void testFindById() {
        Usuario usuario = UsuarioUtils.createComId(1L, "12345678901", "João", StatusRegistro.ATIVO);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1L);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdThrowsException() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.findById(1L));
        assertEquals("Usuário não encontrado com o ID: 1", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        Usuario usuario = UsuarioUtils.create("12345678901", "João", StatusRegistro.ATIVO);

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.create(UsuarioUtils.createDTO("12345678901", "João"));

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testUpdate() {
        Usuario usuarioExistente = UsuarioUtils.createComId(1L, "12345678901", "João", StatusRegistro.ATIVO);
        Usuario usuarioAtualizado = UsuarioUtils.createComId(1L, "12345678901", "João Atualizado", StatusRegistro.ATIVO);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        Usuario result = usuarioService.update(1L, UsuarioUtils.createDTO("12345678901", "João"));

        assertNotNull(result);
        assertEquals("João Atualizado", result.getNome());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testUpdateThrowsExceptionForDifferentCpf() {
        Usuario usuarioExistente = UsuarioUtils.createComId(1L, "12345678901", "João", StatusRegistro.ATIVO);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.update(1L, UsuarioUtils.createDTO("98765432109", "João Atualizado")));
        assertEquals("Não é possivel alterar o CPF", exception.getMessage());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void testDelete() {
        Usuario usuario = UsuarioUtils.createComId(1L, "12345678901", "João", StatusRegistro.ATIVO);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        usuarioService.delete(1L);

        assertEquals(StatusRegistro.REMOVIDO, usuario.getStatus());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testDeleteThrowsExceptionIfAlreadyRemoved() {
        Usuario usuario = UsuarioUtils.createComId(1L, "12345678901", "João", StatusRegistro.REMOVIDO);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.delete(1L));
        assertEquals("Este usuário ja foi removido!", exception.getMessage());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}